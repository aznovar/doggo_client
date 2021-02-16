package com.ru.appdoggo.domain.injectors

import android.content.Context
import com.ru.appdoggo.data.api.account.AccountRequests
import com.ru.appdoggo.data.api.chats.MessagesRequests
import com.ru.appdoggo.data.api.friends.FriendsRequests
import com.ru.appdoggo.data.cache.AccountCache
import com.ru.appdoggo.data.cache.friends.FriendsCache
import com.ru.appdoggo.data.cache.message.MessagesCache
import com.ru.appdoggo.domain.repository.account.AccountRepository
import com.ru.appdoggo.domain.repository.account.AccountRepositoryImpl
import com.ru.appdoggo.domain.repository.chat.MessageRepository
import com.ru.appdoggo.domain.repository.chat.MessageRepositoryImpl
import com.ru.appdoggo.domain.repository.friends.FriendsRepository
import com.ru.appdoggo.domain.repository.friends.FriendsRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideAppContext(): Context = context

    @Provides
    @Singleton
    fun provideAccountRepository(remote: AccountRequests, cache: AccountCache): AccountRepository {
        return AccountRepositoryImpl(remote, cache)
    }

    @Provides
    @Singleton
    fun provideFriendsRepository(remote: FriendsRequests, accCache: AccountCache, frCache: FriendsCache): FriendsRepository {
        return FriendsRepositoryImpl(remote, accCache,frCache)
    }

    @Provides
    @Singleton
    fun provideMessageRepository(remote: MessagesRequests, accCache: AccountCache, messageCache: MessagesCache): MessageRepository{
        return MessageRepositoryImpl(remote,accCache,messageCache)
    }
}