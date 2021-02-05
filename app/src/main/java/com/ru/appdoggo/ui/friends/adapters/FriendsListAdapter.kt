package com.ru.appdoggo.ui.friends.adapters

import android.view.View
import com.ru.appdoggo.R
import com.ru.appdoggo.domain.entities.friends.FriendsEntity
import com.ru.appdoggo.ui.core.BaseAdapter
import kotlinx.android.synthetic.main.item_friend_request.view.*

open class FriendsListAdapter : BaseAdapter<FriendsListAdapter.FriendsListViewHolder>(){

    override val layoutRes = R.layout.item_friends_list

    override fun createHolder(view: View, viewType: Int): FriendsListAdapter.FriendsListViewHolder {
        return FriendsListAdapter.FriendsListViewHolder(view)
    }

    class FriendsListViewHolder(view: View): BaseViewHolder(view){
//        init {
//            view.btnApprove.setOnClickListener {
//                onClick?.onClick(item, it)
//            }
//            view.btnCancel.setOnClickListener {
//                onClick?.onClick(item, it) todo
//            }
//        }

        override fun onBind(item: Any) {
            (item as? FriendsEntity)?.let {
                //  GlideHelper.loadImage(view.context, view.imgPhoto, R.drawable.ic_account_circle)
                //todo прикрутить картинку к изображению пользователя который просится к тебе в друзья
                view.tvName.text = it.name
            }

        }
    }
}