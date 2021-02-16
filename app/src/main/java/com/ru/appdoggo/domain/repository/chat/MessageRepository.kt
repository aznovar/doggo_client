package com.ru.appdoggo.domain.repository.chat

import com.ru.appdoggo.domain.entities.chat.MessageEntity
import com.ru.appdoggo.domain.type.Either
import com.ru.appdoggo.domain.type.Failure
import com.ru.appdoggo.domain.type.None

interface MessageRepository {

    fun sendMessage(toId: Long, message:String) : Either<Failure, None>

    fun getChats(needFetch: Boolean): Either<Failure, List<MessageEntity>>

    fun getMessageWithContact(contactId: Long, needFetch: Boolean): Either<Failure, List<MessageEntity>>
}