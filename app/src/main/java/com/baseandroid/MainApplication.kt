package com.baseandroid

import android.app.Application
import androidx.multidex.MultiDexApplication
import com.baseandroid.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainApplication : MultiDexApplication(), HasAndroidInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Any>


    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent
            .builder()
            .application(this)
            .build()
            .inject(this)
    }
    override fun androidInjector(): AndroidInjector<Any> {
        return activityInjector
    }
}