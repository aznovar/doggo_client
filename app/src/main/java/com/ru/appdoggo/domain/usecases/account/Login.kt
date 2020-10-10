package com.ru.appdoggo.domain.usecases.account

import com.ru.appdoggo.domain.entities.account.AccountEntity
import com.ru.appdoggo.domain.repository.account.AccountRepository
import com.ru.appdoggo.domain.usecases.UseCase
import javax.inject.Inject

class Login @Inject constructor(
    private val repository: AccountRepository
) : UseCase<AccountEntity, Login.Params>() {

    override suspend fun run(params: Params) = repository.login(params.username, params.password)

    data class Params(val username: String, val password: String)
}