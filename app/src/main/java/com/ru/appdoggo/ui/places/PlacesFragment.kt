package com.ru.appdoggo.ui.places

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ru.appdoggo.R
import com.ru.appdoggo.ui.core.BaseFragment

class PlacesFragment : BaseFragment() {
    override val layoutId = R.id.placesFragment
    override val titleToolbar = R.string.title_auth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}