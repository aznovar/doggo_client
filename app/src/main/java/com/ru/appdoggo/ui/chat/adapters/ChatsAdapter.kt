package com.ru.appdoggo.ui.chat.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ru.appdoggo.databinding.ItemChatsBinding
import com.ru.appdoggo.domain.entities.chat.MessageEntity
import com.ru.appdoggo.ui.core.BaseAdapter

open class ChatsAdapter: BaseAdapter<ChatsAdapter.ChatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemChatsBinding.inflate(layoutInflater, parent, false)
        return ChatViewHolder(binding)
    }

    class ChatViewHolder(private val binding: ItemChatsBinding) : BaseViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onClick?.onClick(item, it)
            }
        }

        override fun onBind(item: Any) {
            (item as? MessageEntity)?.let {
                binding.message = it
            }
        }
    }
}