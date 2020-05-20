package com.ru.appdoggo.domain.repository.account

import com.ru.appdoggo.data.api.NetworkResult

interface AccountRepository {
    fun register(username: String, password: String): NetworkResult<String>

    fun login(username: String)

}