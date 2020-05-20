package com.ru.appdoggo.domain.repository.account

import com.ru.appdoggo.data.api.NetworkResult
import com.ru.appdoggo.data.api.account.AccountRequests

class AccountRepositoryImpl(
    private val accRequests: AccountRequests

) : AccountRepository {

    override fun register(username: String, password: String): NetworkResult<String> {
        return accRequests.register(username,password)
    }

    override fun login(username: String) {
        TODO("Not yet implemented")
    }
}