package com.ru.appdoggo.ui.core

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.ru.appdoggo.data.cache.SharedPreferencesManager
import com.ru.appdoggo.ui.chat.ChatFragment
import com.ru.appdoggo.ui.chat.ChatWithUserActivity
import com.ru.appdoggo.ui.login.LoginActivity
import com.ru.appdoggo.ui.main_page.HomeActivity
import com.ru.appdoggo.ui.register.RegisterActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StartPoint
@Inject constructor(sharedPreferences: SharedPreferencesManager) {

    fun showLogin(context: Context, newTask: Boolean = true) =
        context.startActivity<LoginActivity>(newTask = newTask)

    fun showHome(context: Context, newTask: Boolean = true) =
        context.startActivity<HomeActivity>(newTask = newTask)

    fun showSignUp(context: Context) = context.startActivity<RegisterActivity>()

    fun showChatWithContact(contactId: Long, contactName: String, context: Context) {
        val bundle = Bundle()
        bundle.putLong("contact_ id", contactId)
        bundle.putString("contact_name", contactName)
        context.startActivity<ChatWithUserActivity>(args = bundle)
    }

    companion object{
        @JvmStatic
        fun newInstanceOfFragment(contactId: Long, contactName: String) = ChatFragment().apply {
            arguments = Bundle().apply {
                putLong("contact_id", contactId)
                putString("contact_name", contactName)
            }
        }
    }
}

 inline fun <reified T> Context.startActivity(
    newTask: Boolean = false,
    args: Bundle? = null
) {
    this.startActivity(Intent(this, T::class.java).apply {
        if (newTask) {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        putExtra("args", args)
    })
}