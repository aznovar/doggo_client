package com.ru.appdoggo.data.api.account

import com.ru.appdoggo.data.api.NetworkResult
import com.ru.appdoggo.domain.entities.account.AccountEntity
import com.ru.appdoggo.domain.type.Either
import com.ru.appdoggo.domain.type.Failure
import com.ru.appdoggo.domain.type.None

/**
 * Интерфейс описывающий какого рода взаимодействие мы хотим получить для аккаунта с точки зрения http запроса
 */
interface AccountRequests {

    fun register(username: String, password: String): Either<Failure, None>

    fun login(username: String, password: String): Either<Failure, AccountEntity>
}