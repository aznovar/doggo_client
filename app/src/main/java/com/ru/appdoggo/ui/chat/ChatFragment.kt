package com.ru.appdoggo.ui.chat

import android.os.Bundle
import com.ru.appdoggo.App
import com.ru.appdoggo.R
import com.ru.appdoggo.ui.core.BaseFragment

class ChatFragment: BaseFragment()  {

    override val layoutId = R.layout.fragment_chat
    override val titleToolbar = R.string.chat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
    }
}