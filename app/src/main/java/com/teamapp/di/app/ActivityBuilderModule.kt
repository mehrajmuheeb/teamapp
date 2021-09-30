package com.teamapp.di.app

import com.teamapp.di.splash.SplashModule
import com.teamapp.ui.modules.dashboard.MainActivity
import com.teamapp.ui.modules.players.PlayersActivity
import com.teamapp.ui.modules.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = [SplashModule::class])
    abstract fun provideSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    abstract fun provideMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun providePlayerActivity(): PlayersActivity

}