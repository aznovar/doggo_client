package com.ru.appdoggo.data.cache

import com.ru.appdoggo.domain.entities.account.AccountEntity
import com.ru.appdoggo.domain.type.Either
import com.ru.appdoggo.domain.type.Failure
import com.ru.appdoggo.domain.type.None

interface AccountCache {

    fun getToken(): Either<Failure, String>

    fun saveToken(token: String): Either<Failure, None>

    fun saveAccount(account: AccountEntity): Either<Failure, None>

    fun getAccount(): Either<Failure, AccountEntity>

    fun logout(): Either<Failure, None>

}