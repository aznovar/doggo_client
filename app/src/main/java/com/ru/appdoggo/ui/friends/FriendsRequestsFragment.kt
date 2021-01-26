package com.ru.appdoggo.ui.friends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ru.appdoggo.R
import com.ru.appdoggo.ui.core.BaseFragment

class FriendsRequestsFragment: BaseFragment() {

    override val layoutId = R.layout.fragment_friends_requests
    override val titleToolbar = R.string.friends

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_friends_requests, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey("ARG_OBJECT") }?.apply {
//            val textView: TextView = view.findViewById(android.R.id.text1)
//            textView.text = getInt("ARG_OBJECT").toString()
            // todo закоментил, тк ругается во время выполнения на НПЕ
        }
    }
}