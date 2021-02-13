package com.ru.appdoggo.data.api.chats

import com.ru.appdoggo.data.api.BaseResponse
import com.ru.appdoggo.domain.entities.chat.MessageEntity

class GetChatsByUserResponse(
    success: Int,
    message: String,
    val listOfChats: List<MessageEntity>
) : BaseResponse(success, message)