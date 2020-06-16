package com.ru.appdoggo.ui.main_page

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.ru.appdoggo.App
import com.ru.appdoggo.R
import com.ru.appdoggo.data.cache.SharedPreferencesManager
import com.ru.appdoggo.domain.entities.account.AccountEntity
import com.ru.appdoggo.presentation.viewmodel.AccountViewModel
import com.ru.appdoggo.ui.core.BaseFragment
import javax.inject.Inject

class MainPageFragment : BaseFragment() {

    @Inject
    lateinit var sharedPreferencesManager: SharedPreferencesManager

    private lateinit var welcomeTextView: TextView

    private lateinit var accountViewModel: AccountViewModel


    override val layoutId = R.layout.fragment_mainpage
    override val titleToolbar = R.string.title_main_page


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        welcomeTextView = view.findViewById(R.id.text_mainpage)
        val navController = findNavController()
        super.onViewCreated(view, savedInstanceState)
        when(sharedPreferencesManager.containsAnyAccount()){
            true -> showWelcomeMessage()
            false -> navController.navigate(R.id.login)
        }
    }

    @SuppressLint("StringFormatInvalid")
    private fun showWelcomeMessage(){
        welcomeTextView.text = getString(R.string.welcome, "BOI")
    }
}
