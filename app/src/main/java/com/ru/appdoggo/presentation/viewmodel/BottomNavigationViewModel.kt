package com.ru.appdoggo.presentation.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class BottomNavigationViewModel @Inject constructor() : BaseViewModel() {

    private val _bottomNavigationVisibility = MutableLiveData<Int>()
    val bottomNavigationVisibility: LiveData<Int>
        get() = _bottomNavigationVisibility

    fun showBottomNavigation() {
        _bottomNavigationVisibility.postValue(View.VISIBLE)
    }

    fun hideBottomNavigation() {
        _bottomNavigationVisibility.postValue(View.GONE)
    }
}