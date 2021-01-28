package com.ru.appdoggo.ui.friends

import android.os.Bundle
import android.view.View
import com.ru.appdoggo.App
import com.ru.appdoggo.R
import com.ru.appdoggo.domain.type.None
import com.ru.appdoggo.presentation.viewmodel.FriendsViewModel
import com.ru.appdoggo.ui.core.BaseFragment
import com.ru.appdoggo.ui.core.ext.onFailure
import com.ru.appdoggo.ui.core.ext.onSuccess
import kotlinx.android.synthetic.main.fragment_add_friend.*
import kotlinx.android.synthetic.main.fragment_register.*

class AddFriendFragment: BaseFragment() {

    override val layoutId = R.layout.fragment_add_friend
    override val titleToolbar = R.string.title_add_friends

    private lateinit var friendsViewModel: FriendsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)

        friendsViewModel = viewModel {
            onSuccess(addFriendData, ::handleAddFriend)
            //onFailure(failureData, ::handleFailure)//todo пока не работает, как нужно
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnAdd.setOnClickListener {
            sendRequest()
        }
    }

    private fun sendRequest() {
        hideSoftKeyboard()
        friendsViewModel.addFriend(
            etEmail.text.toString()
        )
        showMessage("Запрос отправлен")
    }

    private fun handleAddFriend(none: None?){
        etEmail.text.clear()
        showMessage("Запрос отправлен")
    }
}