package com.ru.appdoggo.domain.usecases.chat

import com.ru.appdoggo.domain.entities.chat.MessageEntity
import com.ru.appdoggo.domain.repository.chat.MessageRepository
import com.ru.appdoggo.domain.usecases.UseCase
import javax.inject.Inject

class GetMessagesWithContact @Inject constructor(
    private val messagesRepository: MessageRepository
) : UseCase<List<MessageEntity>, GetMessagesWithContact.Params>() {

    override suspend fun run(params: Params) =
        messagesRepository.getMessageWithContact(params.contactId, params.needFetch)

    data class Params(val contactId: Long, val needFetch: Boolean)
}