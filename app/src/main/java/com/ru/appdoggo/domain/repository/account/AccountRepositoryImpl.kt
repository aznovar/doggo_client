package com.ru.appdoggo.domain.repository.account

import com.ru.appdoggo.data.api.NetworkResult
import com.ru.appdoggo.data.api.account.AccountRequests
import com.ru.appdoggo.domain.entities.account.AccountEntity
import com.ru.appdoggo.domain.type.Either
import com.ru.appdoggo.domain.type.Failure
import com.ru.appdoggo.domain.type.None

class AccountRepositoryImpl(
    private val accRequests: AccountRequests
) : AccountRepository {

    override fun register(username: String, password: String): Either<Failure, None> {
        return accRequests.register(username, password)
    }

    override fun login(username: String, password: String): Either<Failure, AccountEntity> {
        return accRequests.login(username, password)
    }
}