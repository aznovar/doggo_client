package com.ru.appdoggo.ui.core

import androidx.fragment.app.Fragment
import com.ru.appdoggo.R

abstract class BaseFragment : Fragment(){

    abstract val layoutId: Int

    open val titleToolbar = R.string.app_name

}