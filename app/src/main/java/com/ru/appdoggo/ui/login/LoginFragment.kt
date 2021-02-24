package com.ru.appdoggo.ui.login

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import com.ru.appdoggo.App
import com.ru.appdoggo.R
import com.ru.appdoggo.domain.entities.account.AccountEntity
import com.ru.appdoggo.presentation.viewmodel.AccountViewModel
import com.ru.appdoggo.ui.core.BaseFragment
import com.ru.appdoggo.ui.core.ext.onFailure
import com.ru.appdoggo.ui.core.ext.onSuccess
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.enterEmail
import kotlinx.android.synthetic.main.fragment_register.enterPassword

class LoginFragment : BaseFragment() {

    override val layoutId = R.layout.fragment_login
    override val titleToolbar = R.string.title_auth

    private lateinit var accountViewModel: AccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
        accountViewModel = viewModel {
            onSuccess(accountData, ::handleAccount)
            onFailure(failureData, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_login.setOnClickListener {
//            val action = LoginFragmentDirections.actionLoginFragmentToBaseActivity()
//            view.findNavController().navigate(action)
            login()
        }
    }

    private fun login() {
        hideSoftKeyboard()
        accountViewModel.login(
            enterEmail.text.toString(),
            enterPassword.text.toString()
        )
    }

    private fun handleAccount(acc: AccountEntity?) {
        val action = LoginFragmentDirections.actionLoginFragmentToBaseActivity()
        showMessage("Аккаунт залогинен")
        requireView().findNavController().navigate(action)
    }
}