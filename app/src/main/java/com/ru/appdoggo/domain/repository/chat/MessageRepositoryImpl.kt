package com.ru.appdoggo.domain.repository.chat

import com.ru.appdoggo.data.api.chats.MessagesRequests
import com.ru.appdoggo.data.cache.AccountCache
import com.ru.appdoggo.data.cache.message.MessagesCache
import com.ru.appdoggo.domain.entities.chat.MessageEntity
import com.ru.appdoggo.domain.type.*
import java.security.PrivateKey
import java.util.*
import javax.inject.Inject

class MessageRepositoryImpl @Inject constructor(
    private val messageRequests: MessagesRequests,
    private val accountCache: AccountCache,
    private val messageCache: MessagesCache
) : MessageRepository {

    override fun sendMessage(
        toId: Long,
        message: String
    ): Either<Failure, None> {
        val messageDate = Date().time
        val messageTypeId = 1
        return accountCache.getAccount()
            .flatMap {
                messageRequests.sendMessage(
                    it.id,
                    toId,
                    message,
                    messageDate,
                    messageTypeId
                )
            }
    }

    override fun getChats(needFetch: Boolean): Either<Failure, List<MessageEntity>> {
        return accountCache.getAccount().flatMap { account ->
            return@flatMap if (needFetch) {
                messageRequests.getChats(account.id).onNext {
                    it.map { message ->
                        if (message.senderId == account.id) {
                            message.fromMe = true
                        }
                        messageCache.saveMessage(message)
                    }
                }
            } else {
                Either.Right(messageCache.getChats())
            }
        }.map {
                it.distinctBy {
                    it.contact?.id
                }
            }
        }

    override fun getMessageWithContact(contactId: Long, needFetch: Boolean): Either<Failure, List<MessageEntity>> {
        return accountCache.getAccount().flatMap { account ->
            return@flatMap if (needFetch) {
                messageRequests.getMessagesWithContact(contactId, account.id).onNext {
                    it.map { message ->
                        if (message.senderId == account.id) {
                            message.fromMe = true
                        }
                        val contact = messageCache.getChats().first { it.contact?.id == contactId }.contact
                        message.contact = contact
                        messageCache.saveMessage(message)
                    }
                }
            } else {
                Either.Right(messageCache.getMessagesWithContact(contactId))
            }
        }    }
}