package com.ru.appdoggo.domain.repository.account

import com.ru.appdoggo.domain.type.Either
import com.ru.appdoggo.domain.type.Failure
import com.ru.appdoggo.domain.type.None

interface AccountRepository {
    fun register(username: String, password: String): Either<Failure, None>

    fun login(username: String)

}