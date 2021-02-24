package com.ru.appdoggo.ui.settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import com.ru.appdoggo.App
import com.ru.appdoggo.R
import com.ru.appdoggo.domain.type.None
import com.ru.appdoggo.presentation.viewmodel.AccountViewModel
import com.ru.appdoggo.ui.core.BaseActivity
import com.ru.appdoggo.ui.core.BaseFragment
import com.ru.appdoggo.ui.core.ext.onSuccess
import com.ru.appdoggo.ui.login.LoginActivity
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.navigation.*

class SettingsFragment: BaseFragment() {

    override val layoutId = R.layout.fragment_settings
    override val titleToolbar = R.string.settings

    private lateinit var accountViewModel: AccountViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
        accountViewModel = viewModel{
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logout(view)
    }

    private fun logout(view: View){
        llloginout.setOnClickListener { view ->
            accountViewModel.logout()
            val action = SettingsFragmentDirections.actionLogout()
            view.findNavController().navigate(action)
        }
    }

}