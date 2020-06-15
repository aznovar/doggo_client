package com.ru.appdoggo.ui.main_page

import android.os.Bundle
import android.view.View
import com.ru.appdoggo.App
import com.ru.appdoggo.R
import com.ru.appdoggo.ui.core.BaseFragment

class MainPageFragment : BaseFragment() {

    override val layoutId = R.layout.fragment_mainpage
    override val titleToolbar = R.string.title_main_page


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
