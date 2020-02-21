package com.demo.randomuser

import com.demo.randomuser.di.DaggerRandomAppComponent
import com.facebook.drawee.backends.pipeline.Fresco
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class RandomApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerRandomAppComponent.builder().application(this).build()
    }
    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }


}