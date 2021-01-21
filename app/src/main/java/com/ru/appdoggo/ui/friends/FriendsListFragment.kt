package com.ru.appdoggo.ui.friends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.ru.appdoggo.R
import com.ru.appdoggo.ui.core.BaseFragment

class FriendsListFragment : BaseFragment() {

    override val layoutId = R.layout.fragment_item_friends
    override val titleToolbar = R.string.title_friends

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_item_friends, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey("ARG_OBJECT") }?.apply {
//            val textView: TextView = view.findViewById(android.R.id.text1)
//            textView.text = getInt("ARG_OBJECT").toString()
        }
    }
}