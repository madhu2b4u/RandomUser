package com.demo.randomuser.random.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.randomuser.common.ViewModelFactory
import com.demo.randomuser.di.ViewModelKey
import com.demo.randomuser.random.presentation.viewmodel.RandomListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class RandomListPresentationModule {
    @Binds
    abstract fun bindsViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(RandomListViewModel::class)
    abstract fun bindsViewModel(mRandomListViewModel: RandomListViewModel): ViewModel
}