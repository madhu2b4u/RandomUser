package com.demo.randomuser.di

import android.app.Application
import com.demo.randomuser.RandomApp
import com.demo.randomuser.random.di.RandomListDomainModule
import com.demo.randomuser.random.di.RandomListLocalModule
import com.demo.randomuser.random.di.RandomListPresentationModule
import com.demo.randomuser.random.di.RandomListRemoteModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        NetworkModule::class,
        FragmentBuilderModule::class,
        ActivityBuilderModule::class,
        AppModule::class, RandomListDomainModule::class, RandomListPresentationModule::class, RandomListLocalModule::class, RandomListRemoteModule::class
    ]
)
interface RandomAppComponent : AndroidInjector<RandomApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder

        fun build(): RandomAppComponent
    }

    override fun inject(app: RandomApp)
}