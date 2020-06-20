package com.ru.appdoggo.data.cache

import com.ru.appdoggo.domain.entities.account.AccountEntity
import com.ru.appdoggo.domain.type.Either
import com.ru.appdoggo.domain.type.Failure
import com.ru.appdoggo.domain.type.None
import javax.inject.Inject

class AccountCacheImplementation @Inject constructor(
    private val preferences: SharedPreferencesManager
) : AccountCache {

    override fun getToken(): Either<Failure, String> {
        return preferences.getToken()
    }

    override fun saveToken(token: String): Either<Failure, None> {
        return preferences.saveToken(token)
    }

    override fun saveAccount(account: AccountEntity): Either<Failure, None> {
        return preferences.saveAccount(account)
    }

    override fun getAccount(): Either<Failure, AccountEntity> {
        return preferences.getAccount()
    }

    override fun logout(): Either<Failure, None> {
        return preferences.removeAccount()
    }
}