package com.ru.appdoggo.domain.injectors

import android.content.Context
import com.ru.appdoggo.data.api.account.AccountRequests
import com.ru.appdoggo.domain.repository.account.AccountRepository
import com.ru.appdoggo.domain.repository.account.AccountRepositoryImpl
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
    fun provideAccountRepository(remote: AccountRequests): AccountRepository {
        return AccountRepositoryImpl(remote)
    }
}