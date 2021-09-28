package com.baseandroid.di

import android.app.Application
import com.baseandroid.MainApplication
import com.baseandroid.di.app.ActivityBuilderModule
import com.baseandroid.di.app.AppModule
import com.baseandroid.di.app.RetrofitModule
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