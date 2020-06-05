package com.ru.appdoggo.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.ru.appdoggo.domain.type.None
import com.ru.appdoggo.domain.usecases.Login
import com.ru.appdoggo.domain.usecases.Register
import com.ru.appdoggo.domain.usecases.UseCase
import javax.inject.Inject

class AccountViewModel @Inject constructor(
    val registerUseCase: Register,
    val loginUseCase: Login
) : BaseViewModel() {

    var registerData: MutableLiveData<None> = MutableLiveData()

    fun register(name: String, password: String) {
        registerUseCase(Register.Params(name, password)) {
            it.either(
                ::handleFailure,
                ::handleRegister
            )
        }
    }
    //TODO добавить handleAccount
    fun login(email: String, password: String) {
        loginUseCase(Login.Params(email, password)) {
          //  it.either(::handleFailure)
        }
    }

    private fun handleRegister(none: None) {
        this.registerData.value = none
    }

    override fun onCleared() {
        super.onCleared()
        registerUseCase.unsubscribe()
    }
}
