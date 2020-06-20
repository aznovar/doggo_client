package com.ru.appdoggo.domain.repository.account

import com.ru.appdoggo.data.api.NetworkResult
import com.ru.appdoggo.data.api.account.AccountRequests
import com.ru.appdoggo.data.cache.AccountCache
import com.ru.appdoggo.domain.entities.account.AccountEntity
import com.ru.appdoggo.domain.type.Either
import com.ru.appdoggo.domain.type.Failure
import com.ru.appdoggo.domain.type.None
import com.ru.appdoggo.domain.type.onNext

class AccountRepositoryImpl(
    private val accRequests: AccountRequests,
    private val accCache: AccountCache
) : AccountRepository {

    override fun register(username: String, password: String): Either<Failure, None> {
        return accRequests.register(username, password)
    }

    override fun login(username: String, password: String): Either<Failure, AccountEntity> {
        return accRequests.login(username, password).onNext {
            accCache.saveAccount(it)
        }
    }

    override fun logout(): Either<Failure, None> {
        return accCache.logout()
    }


}