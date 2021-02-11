package com.ru.appdoggo.domain.repository.chat

import com.ru.appdoggo.data.api.chats.MessagesRequests
import com.ru.appdoggo.data.cache.AccountCache
import com.ru.appdoggo.domain.type.Either
import com.ru.appdoggo.domain.type.Failure
import com.ru.appdoggo.domain.type.None
import com.ru.appdoggo.domain.type.flatMap
import java.security.PrivateKey
import javax.inject.Inject

class MessageRepositoryImpl @Inject constructor(
    private val messageRequests: MessagesRequests,
    private val accountCache: AccountCache
): MessageRepository {

    override fun sendMessage(
        toId: Long,
        message: String,
        messageTypeId: Int
    ): Either<Failure, None> {
        return accountCache.getAccount()
            .flatMap { messageRequests.sendMessage(it.id, toId,message,messageTypeId) }
    }
}