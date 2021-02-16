package com.ru.appdoggo.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ru.appdoggo.App
import com.ru.appdoggo.R
import com.ru.appdoggo.data.db.ChatDatabase
import com.ru.appdoggo.domain.entities.chat.MessageEntity
import com.ru.appdoggo.presentation.viewmodel.ChatViewModel
import com.ru.appdoggo.ui.chat.adapters.ChatsAdapter
import com.ru.appdoggo.ui.core.BaseListFragment
import com.ru.appdoggo.ui.core.ext.onFailure
import androidx.lifecycle.Observer

class ChatFragment: BaseListFragment() {

    override val viewAdapter = ChatsAdapter()

    override val titleToolbar = R.string.chat

    private lateinit var chatViewModel: ChatViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chatViewModel = viewModel {
           // onSuccess(progressData, ::updateProgress)
            onFailure(failureData, ::handleFailure)
        }

//        setOnItemClickListener { it, v ->
//            (it as? MessageEntity)?.let {
//                val contact = it.contact
//                if (contact != null) {
//                    navigator.showChatWithContact(contact.id, contact.name, requireActivity())
//                }
//            }
//        }

        ChatDatabase.getInstance(requireContext()).messagesDao.getLiveChats().observe(this, Observer {
            val list = it.distinctBy { it.contact?.id }.toList()
            handleChats(list)
        })
    }

    override fun onResume() {
        super.onResume()
        chatViewModel.getChats()
    }

    fun handleChats(messages: List<MessageEntity>?) {
        if (messages != null) {
            viewAdapter.clear()
            viewAdapter.add(messages)
            viewAdapter.notifyDataSetChanged()
        }
    }
}