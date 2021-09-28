package com.baseandroid.di.app

import com.baseandroid.di.splash.SplashModule
import com.baseandroid.ui.modules.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = [SplashModule::class])
    abstract fun provideSplashActivity(): SplashActivity

}