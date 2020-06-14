package com.ru.appdoggo.ui.core

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ru.appdoggo.R
import com.ru.appdoggo.domain.type.Failure
import com.ru.appdoggo.presentation.viewmodel.BaseViewModel
import com.ru.appdoggo.presentation.viewmodel.BottomNavigationViewModel
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    abstract var fragment: BaseFragment

    @Inject
    lateinit var startPoint: StartPoint

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mainViewModel: BottomNavigationViewModel

    open val contentId = R.layout.activity_layout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupContent()
        setupBottomNavigation()
        addFragment(savedInstanceState)
    }

    open fun setupContent() {
        setContentView(contentId)

    }

    override fun onBackPressed() {
        (supportFragmentManager.findFragmentById(
            R.id.fragmentContainer
        ) as BaseFragment).onBackPressed()
        super.onBackPressed()
    }

    fun hideSoftKeyboard() {
        if (currentFocus != null) {
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
    }

    fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnectionError -> showMessageInToast(getString(R.string.error_network))
            is Failure.ServerError -> showMessageInToast(getString(R.string.error_server))
            is Failure.UsernameAlreadyExist -> showMessageInToast(getString(R.string.error_username_already_exist))
        }
    }

    fun showMessageInToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    inline fun <reified T : ViewModel> viewModel(body: T.() -> Unit): T {
        val vm = ViewModelProviders.of(this, viewModelFactory)[T::class.java]
        vm.body()
        return vm
    }

    fun addFragment(savedInstanceState: Bundle? = null, fragment: BaseFragment = this.fragment) {
        savedInstanceState ?: supportFragmentManager.inTransaction {
            add(R.id.fragmentContainer, fragment)
        }
    }

     fun setupBottomNavigation(){
        mainViewModel = ViewModelProvider(this).get(BottomNavigationViewModel::class.java)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
        mainViewModel.bottomNavigationVisibility.observe(this, Observer { navVisibility ->
            navView.visibility = navVisibility
        })
         navController.addOnDestinationChangedListener { _, destination, _ ->
             when (destination.id) {
                 R.id.loginFragment -> mainViewModel.hideBottomNavigation()
                 else -> mainViewModel.showBottomNavigation()
             }
         }
    }
}

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) =
    beginTransaction().func().commit()

inline fun Activity?.base(block: BaseActivity.() -> Unit) {
    (this as? BaseActivity)?.let(block)
}