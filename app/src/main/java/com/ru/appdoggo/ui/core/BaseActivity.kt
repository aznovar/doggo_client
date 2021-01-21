package com.ru.appdoggo.ui.core

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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
import com.ru.appdoggo.presentation.viewmodel.BottomNavigationViewModel
import kotlinx.android.synthetic.main.activity_navigation.view.*
import javax.inject.Inject

open class BaseActivity : AppCompatActivity() {

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
    }

    open fun setupContent() {
        setContentView(contentId)
    }

    open fun setupBottomNavigation() {
        val navController = findNavController(R.id.nav_host_fragment)
        val navigationView: BottomNavigationView = findViewById(R.id.bottom_nav_view)
        navigationView.setupWithNavController(navController)
        mainViewModel = ViewModelProvider(this).get(BottomNavigationViewModel::class.java)
        mainViewModel.bottomNavigationVisibility.observe(this, Observer { navVisibility ->
            navigationView.visibility = navVisibility
        })
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.login -> mainViewModel.hideBottomNavigation()
                else -> mainViewModel.showBottomNavigation()
            }
        }
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



    fun show(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .commit()
    }
}

inline fun Activity?.base(block: BaseActivity.() -> Unit) {
    (this as? BaseActivity)?.let(block)
}

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) =
    beginTransaction().func().commit()

