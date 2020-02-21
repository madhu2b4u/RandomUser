package com.demo.randomuser.random.di

import android.app.Application
import com.demo.randomuser.random.data.local.database.UsersDatabase
import com.demo.randomuser.random.data.local.source.UsersLocalDataSource
import com.demo.randomuser.random.data.local.source.UsersLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(includes = [RandomListLocalModule.Binders::class])
class RandomListLocalModule {

    @Module
    interface Binders {
        @Binds
        fun bindsLocalDataSource(
            localDataSourceImpl: UsersLocalDataSourceImpl
        ): UsersLocalDataSource
    }

    @Provides
    @Singleton
    fun providesDatabase(
        application: Application
    ) = UsersDatabase.getInstance(application.applicationContext)

    @Provides
    @Singleton
    fun providesUserDao(
        randomDB: UsersDatabase
    ) = randomDB.getUsersDao()


}