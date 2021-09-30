package com.teamapp.di

import android.app.Application
import com.teamapp.MainApplication
import com.teamapp.di.app.ActivityBuilderModule
import com.teamapp.di.app.AppModule
import com.teamapp.di.app.RetrofitModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, RetrofitModule::class, ActivityBuilderModule::class])
interface AppComponent {
    fun inject(application: MainApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}