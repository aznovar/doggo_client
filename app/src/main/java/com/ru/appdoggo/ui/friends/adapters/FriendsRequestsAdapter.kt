package com.ru.appdoggo.ui.friends.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ru.appdoggo.R
import com.ru.appdoggo.databinding.ItemChatsBinding
import com.ru.appdoggo.databinding.ItemFriendRequestBinding
import com.ru.appdoggo.domain.entities.friends.FriendsEntity
import com.ru.appdoggo.ui.chat.adapters.ChatsAdapter
import com.ru.appdoggo.ui.core.BaseAdapter
import kotlinx.android.synthetic.main.item_friend_request.view.*

open class FriendsRequestsAdapter : BaseAdapter<FriendsRequestsAdapter.FriendRequestsViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FriendRequestsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemFriendRequestBinding.inflate(layoutInflater, parent, false)
        return FriendRequestsViewHolder(binding)
    }

    class FriendRequestsViewHolder(val binding: ItemFriendRequestBinding) :
        BaseViewHolder(binding.root) {
        init {
            view.btnApprove.setOnClickListener {
                onClick?.onClick(item, it)
            }
//            view.btnCancel.setOnClickListener {
//                onClick?.onClick(item, it) todo
//            }
        }

        override fun onBind(item: Any) {
            (item as? FriendsEntity)?.let {
                binding.friend = it
            }
        }

    }
}
