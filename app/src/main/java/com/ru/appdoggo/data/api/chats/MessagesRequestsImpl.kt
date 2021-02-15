package com.ru.appdoggo.data.api.chats

import com.ru.appdoggo.data.api.ApiService
import com.ru.appdoggo.data.api.BaseRequest
import com.ru.appdoggo.data.api.account.SendMessageDataPost
import com.ru.appdoggo.domain.entities.chat.MessageEntity
import com.ru.appdoggo.domain.type.Either
import com.ru.appdoggo.domain.type.Failure
import com.ru.appdoggo.domain.type.None
import javax.inject.Inject

class MessagesRequestsImpl @Inject constructor(
    private val apiService: ApiService,
    private val requests: BaseRequest
) : MessagesRequests {

    override fun sendMessage(fromId: Long, toId: Long, message: String, messageDate: Long, messageTypeId: Int): Either<Failure, None> {
        return requests.make(apiService.sendMessage(SendMessageDataPost(fromId,toId,message,messageDate,messageTypeId)))
        {None()}
    }

    override fun getChats(userId: Long): Either<Failure, List<MessageEntity>> {
        return requests.make(apiService.getChatsByUser(userId)){it.listOfChats}
    }
}