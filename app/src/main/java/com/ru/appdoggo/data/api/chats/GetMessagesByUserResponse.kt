package com.ru.appdoggo.data.api.chats

import com.ru.appdoggo.data.api.BaseResponse
import com.ru.appdoggo.domain.entities.chat.MessageEntity

class GetMessagesByUserResponse(
    success: Int,
    message: String,
    val listOfMessages: List<MessageEntity>
) : BaseResponse(success, message)

