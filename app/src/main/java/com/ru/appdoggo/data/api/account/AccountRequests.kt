package com.ru.appdoggo.data.api.account

import com.ru.appdoggo.data.api.NetworkResult
import com.ru.appdoggo.domain.type.Either
import com.ru.appdoggo.domain.type.Failure
import com.ru.appdoggo.domain.type.None

/**
 * Интерфейс описывающий какого рода взаимодействие мы хотим получить для аккаунта
 */
interface AccountRequests {

    fun register(username: String, password: String): Either<Failure,None>

    fun login(username: String)
}