package com.ru.appdoggo

import android.app.Application
import com.ru.appdoggo.domain.injectors.ApiModule
import com.ru.appdoggo.domain.injectors.ApplicationModule
import com.ru.appdoggo.domain.injectors.ViewModelModule
import com.ru.appdoggo.ui.register.RegisterActivity
import com.ru.appdoggo.ui.register.RegisterFragment
import dagger.Component
import javax.inject.Singleton

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        initAppComponent()
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .applicationModule(ApplicationModule(this)).build()
    }
}

@Singleton
@Component(modules = [ApplicationModule::class, ApiModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(activity: RegisterActivity)

    fun inject(fragment: RegisterFragment)
}
