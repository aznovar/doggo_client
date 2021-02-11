package com.ru.appdoggo.domain.injectors

import android.content.Context
import android.content.SharedPreferences
import com.ru.appdoggo.data.cache.AccountCache
import com.ru.appdoggo.data.cache.AccountCacheImplementation
import com.ru.appdoggo.data.cache.SharedPreferencesManager
import com.ru.appdoggo.data.cache.message.MessagesCache
import com.ru.appdoggo.data.db.ChatDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun provideAccountCache(prefsManager: SharedPreferencesManager): AccountCache =
        AccountCacheImplementation(prefsManager)

    @Provides
    @Singleton
    fun provideMessagesCache(chatDatabase: ChatDatabase): MessagesCache {
        return chatDatabase.messagesDao
    }
}