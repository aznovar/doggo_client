package com.ru.appdoggo.ui.login

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.ru.appdoggo.R
import com.ru.appdoggo.presentation.viewmodel.BottomNavigationViewModel
import com.ru.appdoggo.ui.core.BaseActivity
import com.ru.appdoggo.ui.core.BaseFragment
import com.ru.appdoggo.ui.core.StartPoint
import kotlinx.android.synthetic.main.activity_layout.*
import kotlinx.android.synthetic.main.top_toolbar.*
import javax.inject.Inject

open class LoginActivity : AppCompatActivity() {

     var fragment: BaseFragment = LoginFragment()

     @Inject
     lateinit var startPoint: StartPoint

     @Inject
     lateinit var viewModelFactory: ViewModelProvider.Factory

     private lateinit var mainViewModel: BottomNavigationViewModel

     private lateinit var appBarConfiguration: AppBarConfiguration

     private lateinit var navController: NavController

     open val contentId = R.layout.activity_authentication

     override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          setupContent()
          setupNavigation()
     }

     open fun setupContent() {
          setContentView(contentId)
     }

     open fun setupNavigation() {
          navController = findNavController(R.id.nav_fragment_auth)
     }

     private fun showBothNavigation() {
          side_navigation_view?.visibility = View.VISIBLE
          bottom_nav_view?.visibility = View.VISIBLE
          drawerLayout?.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
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
}