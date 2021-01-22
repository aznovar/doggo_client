package com.ru.appdoggo.ui.settings

import android.os.Bundle
import android.view.View
import com.ru.appdoggo.App
import com.ru.appdoggo.R
import com.ru.appdoggo.ui.core.BaseFragment

class SettingsFragment: BaseFragment() {

    override val layoutId = R.layout.fragment_settings
    override val titleToolbar = R.string.settings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}