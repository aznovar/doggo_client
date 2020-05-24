package com.ru.appdoggo.ui.register

import android.os.Bundle
import android.view.View
import com.ru.appdoggo.App
import com.ru.appdoggo.R
import com.ru.appdoggo.domain.type.None
import com.ru.appdoggo.presentation.viewmodel.AccountViewModel
import com.ru.appdoggo.ui.core.BaseFragment
import com.ru.appdoggo.ui.core.ext.onSuccess
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : BaseFragment() {

    override val layoutId = R.layout.fragment_register
    override val titleToolbar = R.string.title_reg

    private lateinit var accountViewModel: AccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
        accountViewModel = viewModel {
            onSuccess(registerData, ::handleRegister)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button.setOnClickListener {
            register()
        }
    }

    private fun register() {
        hideSoftKeyboard()
        accountViewModel.register(
            enterEmail.text.toString(),
            enterPassword.text.toString()
        )
    }

    private fun handleRegister(none: None? = None()) {
    }
}

