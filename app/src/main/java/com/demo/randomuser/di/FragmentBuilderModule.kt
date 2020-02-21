package com.demo.randomuser.di

import com.demo.randomuser.random.presentation.ui.fragments.RandomListFragment
import com.demo.randomuser.random.presentation.ui.fragments.UserDetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributesRandomListFragment(): RandomListFragment

    @ContributesAndroidInjector
    abstract fun contributesUserDetailsFragmentFragment(): UserDetailsFragment

}