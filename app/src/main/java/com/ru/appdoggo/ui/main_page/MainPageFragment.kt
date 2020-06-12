package com.ru.appdoggo.ui.main_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ru.appdoggo.App
import com.ru.appdoggo.R
import com.ru.appdoggo.ui.core.BaseFragment
import kotlinx.android.synthetic.main.fragment_register.*

class MainPageFragment : BaseFragment() {
    override val layoutId = R.id.mainPageFragment
    override val titleToolbar = R.string.title_main_page


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
