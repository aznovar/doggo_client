package com.ru.appdoggo.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.ru.appdoggo.domain.entities.chat.MessageEntity
import com.ru.appdoggo.domain.type.None
import com.ru.appdoggo.domain.usecases.chat.GetChats
import com.ru.appdoggo.domain.usecases.chat.SendMessage
import javax.inject.Inject

class ChatViewModel @Inject constructor(
    val getChatsUseCase: GetChats,
    val sendMessageUseCase: SendMessage
) : BaseViewModel() {

    val sendMessageData: MutableLiveData<None> = MutableLiveData()
    val getChatsData: MutableLiveData<List<MessageEntity>> = MutableLiveData()

    fun getChats(needFetch: Boolean = false) {
        getChatsUseCase(GetChats.Params(needFetch)) { it.either(::handleFailure) { handleGetChats(it, !needFetch)} }
    }

    fun sendMessage(toId: Long, message: String) {
        sendMessageUseCase(SendMessage.Params(toId, message))
        { it.either(::handleFailure) { handleSendMessage(it, toId) } }
    }

    private fun handleSendMessage(none: None, contactId: Long) {
        sendMessageData.value = none
        // todo add getMessages(contactId, true)
    }

    private fun handleGetChats(messages: List<MessageEntity>, fromCache: Boolean){
        getChatsData.value = messages
        updateProgress(false)

        if (fromCache) {
            updateProgress(true)
            getChats(true)
        }
    }
}