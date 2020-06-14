package com.ru.appdoggo.ui.core

import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.navigation.NavigationView
import com.ru.appdoggo.App
import com.ru.appdoggo.R
import com.ru.appdoggo.ui.login.LoginFragment

class MainActivity : BaseActivity() {

    private val idContent = R.id.login

    override val contentId: Int
        get() = super.contentId

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
        //navigateToDestination(idContent)
    }



}



