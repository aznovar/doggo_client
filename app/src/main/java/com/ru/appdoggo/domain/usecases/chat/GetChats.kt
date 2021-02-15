package com.ru.appdoggo.domain.usecases.chat

import com.ru.appdoggo.domain.entities.chat.MessageEntity
import com.ru.appdoggo.domain.repository.chat.MessageRepository
import com.ru.appdoggo.domain.usecases.UseCase
import javax.inject.Inject

class GetChats @Inject constructor(
    private val messageRepository: MessageRepository
) : UseCase<List<MessageEntity>, GetChats.Params>() {

    override suspend fun run(params: Params) = messageRepository.getChats(params.needFetch)

    data class Params(val needFetch: Boolean)
}