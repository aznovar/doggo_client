package com.ru.appdoggo.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.ru.appdoggo.domain.type.None
import com.ru.appdoggo.domain.usecases.chat.SendMessage
import javax.inject.Inject

class ChatViewModel @Inject constructor(
    private val sendMessageUseCase: SendMessage
) : BaseViewModel() {

    val sendMessageData: MutableLiveData<None> = MutableLiveData()


    fun sendMessage(toId: Long, message: String) {
        sendMessageUseCase(SendMessage.Params(toId, message))
        { it.either(::handleFailure) { handleSendMessage(it, toId) } }
    }


    private fun handleSendMessage(none: None, contactId: Long) {
        sendMessageData.value = none
        // todo add getMessages(contactId, true)
    }
}