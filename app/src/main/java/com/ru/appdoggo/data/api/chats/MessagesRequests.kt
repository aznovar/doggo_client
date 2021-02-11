package com.ru.appdoggo.data.api.chats

import com.ru.appdoggo.domain.type.Either
import com.ru.appdoggo.domain.type.Failure
import com.ru.appdoggo.domain.type.None

interface MessagesRequests {

    fun sendMessage(fromId: Long, toId: Long, message: String, messageTypeId: Int): Either<Failure, None>
}