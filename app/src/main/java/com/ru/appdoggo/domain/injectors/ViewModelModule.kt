package com.ru.appdoggo.domain.injectors

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ru.appdoggo.presentation.viewmodel.AccountViewModel
import com.ru.appdoggo.presentation.viewmodel.BottomNavigationViewModel
import com.ru.appdoggo.presentation.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AccountViewModel::class)
    abstract fun bindAccountViewModel(accountViewModel: AccountViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BottomNavigationViewModel::class)
    abstract fun bindBottomNavigationViewModel(bottomNavigationViewModel: BottomNavigationViewModel): ViewModel
}