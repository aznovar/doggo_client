package com.ru.appdoggo.domain.repository.account

import com.ru.appdoggo.data.api.NetworkResult
import com.ru.appdoggo.data.api.account.AccountRequests
import com.ru.appdoggo.domain.type.Either
import com.ru.appdoggo.domain.type.Failure

class AccountRepositoryImpl(
    private val accRequests: AccountRequests

) : AccountRepository {

    override fun register(username: String, password: String): Either<Failure,Nothing> {
        return accRequests.register(username,password)
    }

    override fun login(username: String) {
        TODO("Not yet implemented")
    }
}