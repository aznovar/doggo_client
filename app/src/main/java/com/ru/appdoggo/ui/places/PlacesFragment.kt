package com.ru.appdoggo.ui.places

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ru.appdoggo.App
import com.ru.appdoggo.R
import com.ru.appdoggo.ui.core.BaseFragment

class PlacesFragment : BaseFragment() {
    override val layoutId = R.layout.fragment_places
    override val titleToolbar = R.string.title_auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}