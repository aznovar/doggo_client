package com.ru.appdoggo.data.api.chats

import com.ru.appdoggo.domain.entities.chat.MessageEntity
import com.ru.appdoggo.domain.type.Either
import com.ru.appdoggo.domain.type.Failure
import com.ru.appdoggo.domain.type.None

interface MessagesRequests {

    fun sendMessage(fromId: Long, toId: Long, message: String, messageDate: Long, messageTypeId: Int): Either<Failure, None>

    fun getChats(userId: Long): Either<Failure, List<MessageEntity>>

    fun getMessagesWithContact(contactId: Long, userId: Long): Either<Failure, List<MessageEntity>>
}