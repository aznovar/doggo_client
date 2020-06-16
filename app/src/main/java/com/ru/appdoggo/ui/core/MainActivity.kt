package com.ru.appdoggo.ui.core

import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.ru.appdoggo.App
import com.ru.appdoggo.R
import com.ru.appdoggo.data.cache.SharedPreferencesManager
import com.ru.appdoggo.ui.login.LoginFragment
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var sharedPreferencesManager: SharedPreferencesManager

    override val contentId: Int
        get() = super.contentId

    override fun onCreate(savedInstanceState: Bundle?) {
        val navController = findNavController(R.id.nav_host_fragment)
        val navigationView: BottomNavigationView = findViewById(R.id.bottom_nav_view)
        navigationView.setupWithNavController(navController)
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
        when(sharedPreferencesManager.containsAnyAccount()){
            true -> navController.navigate(R.id.mainPage)
            false -> navController.navigate(R.id.login)
        }
    }



}



