package com.ru.appdoggo.data.cache.message

import com.ru.appdoggo.domain.entities.chat.MessageEntity

interface MessagesCache {

    fun saveMessage(entity: MessageEntity)

    fun getChats(): List<MessageEntity>

    fun getMessagesWithContact(contactId: Long): List<MessageEntity>
}