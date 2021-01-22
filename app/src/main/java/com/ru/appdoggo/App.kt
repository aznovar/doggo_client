package com.ru.appdoggo

import android.app.Application
import com.ru.appdoggo.domain.injectors.ApiModule
import com.ru.appdoggo.domain.injectors.ApplicationModule
import com.ru.appdoggo.domain.injectors.CacheModule
import com.ru.appdoggo.domain.injectors.ViewModelModule
import com.ru.appdoggo.ui.chat.ChatFragment
import com.ru.appdoggo.ui.core.BaseActivity
import com.ru.appdoggo.ui.core.StartActivity
import com.ru.appdoggo.ui.friends.FriendsFragment
import com.ru.appdoggo.ui.friends.FriendsListFragment
//import com.ru.appdoggo.ui.login.LoginActivity
import com.ru.appdoggo.ui.login.LoginFragment
//import com.ru.appdoggo.ui.main_page.HomeActivity
import com.ru.appdoggo.ui.main_page.MainPageFragment
import com.ru.appdoggo.ui.places.PlacesFragment
import com.ru.appdoggo.ui.profile.ProfileActivity
import com.ru.appdoggo.ui.profile.ProfileFragment
//import com.ru.appdoggo.ui.register.RegisterActivity
import com.ru.appdoggo.ui.register.RegisterFragment
import com.ru.appdoggo.ui.settings.SettingsFragment
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
@Component(
    modules = [ApplicationModule::class, ApiModule::class, ViewModelModule::class,
        CacheModule::class]
)
interface AppComponent {

    //fun inject(activity: RegisterActivity)

    fun inject(fragment: RegisterFragment)

   // fun inject(activity: LoginActivity)

    fun inject(activity: StartActivity)

    //fun inject(activity: HomeActivity)

    fun inject(activity: BaseActivity)

    fun inject(activity: ProfileActivity)

    fun inject(fragment: LoginFragment)

    fun inject(fragment: MainPageFragment)

    fun inject(fragment: ProfileFragment)

    fun inject(fragment: PlacesFragment)

    fun inject(fragment: ChatFragment)

    fun inject(fragment: SettingsFragment)

    fun inject(fragment: FriendsFragment)

    fun inject(fragment: FriendsListFragment)


}
