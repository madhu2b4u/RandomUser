package com.demo.randomuser.random.di

import com.demo.randomuser.random.data.repository.RandomListRepository
import com.demo.randomuser.random.data.repository.RandomListRepositoryImpl
import com.demo.randomuser.random.domain.RandomListUseCase
import com.demo.randomuser.random.domain.RandomListUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RandomListDomainModule {

    @Binds
    abstract fun bindsRepository(
        repoImpl: RandomListRepositoryImpl
    ): RandomListRepository


    @Binds
    abstract fun bindsUsersUseCase(
        mRandomListUseCase: RandomListUseCaseImpl
    ): RandomListUseCase


}