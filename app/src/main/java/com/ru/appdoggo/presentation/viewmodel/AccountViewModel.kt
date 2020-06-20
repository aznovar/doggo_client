package com.ru.appdoggo.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.ru.appdoggo.domain.entities.account.AccountEntity
import com.ru.appdoggo.domain.type.None
import com.ru.appdoggo.domain.usecases.Login
import com.ru.appdoggo.domain.usecases.Logout
import com.ru.appdoggo.domain.usecases.Register
import javax.inject.Inject

class AccountViewModel @Inject constructor(
    val registerUseCase: Register,
    val loginUseCase: Login,
    val logoutUseCase: Logout
) : BaseViewModel() {

    var registerData: MutableLiveData<None> = MutableLiveData()
    var accountData: MutableLiveData<AccountEntity> = MutableLiveData()
    var logoutData: MutableLiveData<None> = MutableLiveData()

    fun register(name: String, password: String) {
        registerUseCase(Register.Params(name, password)) {
            it.either(
                ::handleFailure,
                ::handleRegister
            )
        }
    }

    fun login(email: String, password: String) {
        loginUseCase(Login.Params(email, password)) {
            it.either(::handleFailure, ::handleAccount)
        }
    }

    fun logout() {
        logoutUseCase(None()) { it.either(::handleFailure, ::handleLogout) }
    }

    private fun handleRegister(none: None) {
        this.registerData.value = none
    }

    private fun handleAccount(account: AccountEntity) {
        this.accountData.value = account
    }

    private fun handleLogout(none: None) {
        this.logoutData.value = none
    }

    override fun onCleared() {
        super.onCleared()
        registerUseCase.unsubscribe()
        loginUseCase.unsubscribe()
    }
}
