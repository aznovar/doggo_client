package com.ru.appdoggo.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.ru.appdoggo.domain.entities.account.AccountEntity
import com.ru.appdoggo.domain.type.None
import com.ru.appdoggo.domain.usecases.Login
import com.ru.appdoggo.domain.usecases.Register
import javax.inject.Inject

class AccountViewModel @Inject constructor(
    val registerUseCase: Register,
    val loginUseCase: Login
) : BaseViewModel() {

    var registerData: MutableLiveData<None> = MutableLiveData()
    var accountData: MutableLiveData<AccountEntity> = MutableLiveData()

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

    private fun handleRegister(none: None) {
        this.registerData.value = none
    }

    private fun handleAccount(account: AccountEntity) {
        this.accountData.value = account
    }

    override fun onCleared() {
        super.onCleared()
        registerUseCase.unsubscribe()
        loginUseCase.unsubscribe()
    }
}
