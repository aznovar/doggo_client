package com.ru.appdoggo.ui.core

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.ru.appdoggo.App
import javax.inject.Inject

class StartActivity : AppCompatActivity() {

    @Inject
    lateinit var startPoint: StartPoint


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
    }
}