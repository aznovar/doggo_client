package com.ru.appdoggo.ui.chat

import android.os.Bundle
import com.ru.appdoggo.App
import com.ru.appdoggo.ui.core.BaseActivity
import com.ru.appdoggo.ui.core.BaseFragment

class ChatWithUserActivity: BaseActivity() {

    var fragment: BaseFragment = MessageFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
    }
}