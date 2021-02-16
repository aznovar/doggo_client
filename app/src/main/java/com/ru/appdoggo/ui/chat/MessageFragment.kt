package com.ru.appdoggo.ui.chat

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ru.appdoggo.App
import com.ru.appdoggo.R
import com.ru.appdoggo.data.db.ChatDatabase
import com.ru.appdoggo.domain.entities.chat.MessageEntity
import com.ru.appdoggo.presentation.viewmodel.ChatViewModel
import com.ru.appdoggo.ui.chat.adapters.MessageAdapter
import com.ru.appdoggo.ui.core.BaseFragment
import com.ru.appdoggo.ui.core.ext.onFailure
import com.ru.appdoggo.ui.core.ext.onSuccess
import kotlinx.android.synthetic.main.fragment_messages.*


class MessageFragment: BaseFragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var lm: RecyclerView.LayoutManager
    lateinit var chatViewModel: ChatViewModel

    private val viewAdapter = MessageAdapter()
    override val layoutId = R.layout.fragment_messages

    private var contactId = 0L
    private var contactName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lm = LinearLayoutManager(context)

        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView).apply {
            setHasFixedSize(true)
            layoutManager = lm
            adapter = viewAdapter
        }

        (lm as? LinearLayoutManager)?.apply {
            stackFromEnd = true
        }

        chatViewModel = viewModel {
            onSuccess(getMessagesWithContactData, ::handleMessages)
            onSuccess(progressData, ::updateProgress)
            onFailure(failureData, ::handleFailure)
        }

        base {
            val args = intent.getBundleExtra("args")

            if (args != null) {
                contactId = args.getLong("contact_id")
                contactName = args.getString("contact_name", "")
            } else {
                contactId = intent.getLongExtra("contact_id", 0L)
                contactName = intent.getStringExtra("contact_name")
            }
        }

        btnSend.setOnClickListener {
            sendMessage()
        }

        ChatDatabase.getInstance(requireContext()).messagesDao.getLiveMessagesWithContact(contactId).observe(this, Observer {
            handleMessages(it)
        })
    }

    override fun onResume() {
        super.onResume()
        base {
            supportActionBar?.title = contactName
        }

        chatViewModel.getMessagesWithContact(contactId)
    }

    private fun handleMessages(messages: List<MessageEntity>?) {
        if (messages != null) {
            viewAdapter.submitList(messages)
            Handler().postDelayed({
                recyclerView.smoothScrollToPosition(viewAdapter.itemCount - 1)
            }, 100)
        }
    }

    private fun sendMessage() {
        if (contactId == 0L) return
        val text = etText.text.toString()
        if (text.isBlank()) {
            showMessage("Введите текст")
            return
        }
        showProgress()
        chatViewModel.sendMessage(contactId, text)
        etText.text.clear()
    }
}