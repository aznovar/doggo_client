package com.ru.appdoggo.domain.usecases.chat

import com.ru.appdoggo.domain.repository.chat.MessageRepository
import com.ru.appdoggo.domain.type.None
import com.ru.appdoggo.domain.usecases.UseCase
import javax.inject.Inject

class SendMessage @Inject constructor(
    private val messageRepository: MessageRepository
): UseCase<None, SendMessage.Params>() {

    override suspend fun run(params: Params) = messageRepository.sendMessage(params.toId, params.message, params.messageTypeId)

    data class Params(val toId: Long, val message: String, val messageTypeId: Int)
}