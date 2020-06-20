package com.ru.appdoggo.data.cache

import android.content.SharedPreferences
import android.drm.DrmInfoRequest.ACCOUNT_ID
import com.ru.appdoggo.domain.entities.account.AccountEntity
import com.ru.appdoggo.domain.type.Either
import com.ru.appdoggo.domain.type.Failure
import com.ru.appdoggo.domain.type.None
import javax.inject.Inject

class SharedPreferencesManager @Inject constructor(private val preferences: SharedPreferences) {

    companion object {
        const val ACC_TOKEN = "acc_token"
        const val ACC_NAME = "acc_name"
        const val ACC_ID = "acc_id"
        const val ACC_STATUS = "acc_status"
        const val ACC_DATE = "acc_date"
    }

    fun saveToken(token: String): Either<Failure, None> {
        preferences.edit().apply {
            putString(ACC_TOKEN, token)
        }.apply()

        return Either.Right(None())
    }

    fun getToken(): Either<Failure, String> {
        return Either.Right(preferences.getString(ACC_TOKEN, ""))
    }

    fun saveAccount(account: AccountEntity): Either<Failure, None> {
        preferences.edit().apply {
            putLong(ACC_ID, account.id)
            putString(ACC_NAME, account.name)
            putString(ACC_TOKEN, account.token)
            putString(ACC_DATE, account.registerDate)
            putString(ACC_STATUS, account.status)
        }.apply()
        return Either.Right(None())
    }

    fun getAccount(): Either<Failure, AccountEntity> {
        val id = preferences.getLong(ACC_ID, 0)

        if (id == 0L) {
            return Either.Left(Failure.NoSavedAccountsError)
        }

        val account = AccountEntity(
            preferences.getLong(ACC_ID, 0),
            preferences.getString(ACC_NAME, ""),
            preferences.getString(ACC_TOKEN, ""),
            preferences.getString(ACC_DATE, ""),
            preferences.getString(ACC_STATUS, "")
        )
        return Either.Right(account)
    }

    fun containsAnyAccount(): Boolean {
        val id = preferences.getString(ACC_STATUS, "")
        return id != ""
    }

    fun removeAccount(): Either<Failure, None> {
        preferences.edit().apply {
            remove(ACC_ID)
            remove(ACC_NAME)
            remove(ACC_DATE)
            remove(ACC_STATUS)
        }.apply()

        return Either.Right(None())
    }
}