package com.ru.appdoggo.domain.repository.account

import com.ru.appdoggo.domain.type.Either
import com.ru.appdoggo.domain.type.Failure

interface AccountRepository {
    fun register(username: String, password: String): Either<Failure,Nothing>

    fun login(username: String)

}