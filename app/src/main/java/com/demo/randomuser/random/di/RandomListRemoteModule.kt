package com.demo.randomuser.random.di


import com.demo.randomuser.random.data.remote.services.RandomListService
import com.demo.randomuser.random.data.remote.source.RandomListRemoteDataSource
import com.demo.randomuser.random.data.remote.source.RandomListRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


@Module(includes = [RandomListRemoteModule.Binders::class])
class RandomListRemoteModule {
    @Module
    interface Binders {
        @Binds
        fun bindsRemoteSource(
            remoteDataSourceImpl: RandomListRemoteDataSourceImpl
        ): RandomListRemoteDataSource
    }

    @Provides
    fun providesRandomListService(retrofit: Retrofit): RandomListService =
        retrofit.create(RandomListService::class.java)


}