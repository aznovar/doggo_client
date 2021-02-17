package com.ru.appdoggo.ui.friends.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ru.appdoggo.R
import com.ru.appdoggo.databinding.ItemFriendRequestBinding
import com.ru.appdoggo.databinding.ItemFriendsListBinding
import com.ru.appdoggo.domain.entities.friends.FriendsEntity
import com.ru.appdoggo.ui.core.BaseAdapter
import kotlinx.android.synthetic.main.item_friend_request.view.*

open class FriendsListAdapter : BaseAdapter<FriendsListAdapter.FriendsListViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemFriendsListBinding.inflate(layoutInflater, parent, false)
        return FriendsListViewHolder(binding)
    }

    class FriendsListViewHolder(val binding: ItemFriendsListBinding) :
        BaseViewHolder(binding.root){
//        init {
//            view.btnApprove.setOnClickListener {
//                onClick?.onClick(item, it)
//            }
//            view.btnCancel.setOnClickListener {
//                onClick?.onClick(item, it) todo
//            }
    //    }
        override fun onBind(item: Any) {
            (item as? FriendsEntity)?.let {
                binding.friend = it
            }
        }
    }
}