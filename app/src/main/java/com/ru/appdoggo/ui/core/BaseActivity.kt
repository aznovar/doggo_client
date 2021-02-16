package com.ru.appdoggo.ui.core

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ru.appdoggo.R
import com.ru.appdoggo.domain.type.Failure
import com.ru.appdoggo.presentation.viewmodel.BottomNavigationViewModel
import kotlinx.android.synthetic.main.activity_layout.*
import kotlinx.android.synthetic.main.activity_navigation.view.*
import kotlinx.android.synthetic.main.top_toolbar.*
import javax.inject.Inject

open class BaseActivity :
    AppCompatActivity() {//todo переделать в активити, которая содержит в себе все фрагменты после авторизации

    @Inject
    lateinit var startPoint: StartPoint

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mainViewModel: BottomNavigationViewModel

    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var navController: NavController

    open val contentId = R.layout.activity_layout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupContent()
        setupNavigation()
    }

    open fun setupContent() {
        setContentView(contentId)
    }

    open fun setupNavigation() {
        navController = findNavController(R.id.nav_fragment)
        appBarConfiguration = AppBarConfiguration.Builder(
            R.id.settings, R.id.mainPage, R.id.places
        )
            .setDrawerLayout(drawerLayout)
            .build()
        setSupportActionBar(toolbar) //Set toolbar
        setupActionBarWithNavController(navController, appBarConfiguration)
        visibilityNavElements(navController)
    }

    private fun visibilityNavElements(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
              //  R.id.chat -> hideToolbar()
                R.id.friends -> hideBottomNavigation()
                R.id.addFriend -> hideBottomNavigation()
                R.id.login -> hideBothNavigation()
                else -> showBothNavigation()
            }
        }
    }

    private fun hideBothNavigation() { //Hide both drawer and bottom navigation bar
        side_navigation_view?.visibility = View.GONE
        bottom_nav_view?.visibility = View.GONE
        drawerLayout?.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED) //To lock navigation drawer so that it don't respond to swipe gesture
    }

    private fun hideToolbar(){
        toolbar?.visibility = View.GONE

    }

    private fun hideBottomNavigation(){
        bottom_nav_view?.visibility = View.GONE
    }

    private fun showBothNavigation() {
        side_navigation_view?.visibility = View.VISIBLE
        bottom_nav_view?.visibility = View.VISIBLE
        drawerLayout?.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        setupNavControl() //To configure navController with drawer and bottom navigation
    }

    private fun setupNavControl() {
        side_navigation_view?.setupWithNavController(navController) //Setup Drawer navigation with navController
        bottom_nav_view?.setupWithNavController(navController) //Setup Bottom navigation with navController
    }

    override fun onSupportNavigateUp(): Boolean { //Setup appBarConfiguration for back arrow
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    override fun onBackPressed() {
        when { //If drawer layout is open close that on back pressed
            drawerLayout.isDrawerOpen(GravityCompat.START) -> {
                drawerLayout.closeDrawer(GravityCompat.START)
            }
            else -> {
                super.onBackPressed() //If drawer is already in closed condition then go back
            }
        }
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

}

inline fun Activity?.base(block: BaseActivity.() -> Unit) {
    (this as? BaseActivity)?.let(block)
}

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) =
    beginTransaction().func().commit()

