package com.ru.appdoggo.data.api.account

import com.ru.appdoggo.data.api.NetworkResult

/**
 * Интерфейс описывающий какого рода взаимодействие мы хотим получить для аккаунта
 */
interface AccountRequests {

    fun register(username: String, password: String): NetworkResult<String>

    fun login(username: String)
}