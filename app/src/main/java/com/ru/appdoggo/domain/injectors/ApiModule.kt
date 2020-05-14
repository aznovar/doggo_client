package com.ru.appdoggo.domain.injectors

import com.ru.appdoggo.data.api.ApiService
import com.ru.appdoggo.data.api.ServiceFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApiModule {

    @Singleton
    @Provides
    fun providesApiService(): ApiService = ServiceFactory.makeService(true)
}