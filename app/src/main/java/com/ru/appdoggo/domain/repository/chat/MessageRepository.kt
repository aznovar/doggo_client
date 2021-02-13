package com.ru.appdoggo.domain.repository.chat

import com.ru.appdoggo.domain.type.Either
import com.ru.appdoggo.domain.type.Failure
import com.ru.appdoggo.domain.type.None

interface MessageRepository {

    fun sendMessage(toId: Long, message:String) : Either<Failure, None>
}