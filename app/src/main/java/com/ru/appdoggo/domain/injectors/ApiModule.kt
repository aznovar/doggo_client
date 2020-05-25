package com.ru.appdoggo.domain.injectors

import com.ru.appdoggo.data.api.ApiService
import com.ru.appdoggo.data.api.BaseRequest
import com.ru.appdoggo.data.api.ServiceFactory
import com.ru.appdoggo.data.api.account.AccountRequests
import com.ru.appdoggo.data.api.account.AccountRequestsImpl
import dagger.Module
import dagger.Provides
import okhttp3.Request
import javax.inject.Singleton

@Module
class ApiModule {

    @Singleton
    @Provides
    fun providesApiService(): ApiService = ServiceFactory.makeService(true)

    @Singleton
    @Provides
    fun provideAccountRemote(apiService: ApiService, request: BaseRequest): AccountRequests {
        return AccountRequestsImpl(apiService, request)
    }
}