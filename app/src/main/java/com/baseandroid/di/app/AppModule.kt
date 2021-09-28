package com.baseandroid.di.app

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.baseandroid.data.local.PreferencesHelper
import com.baseandroid.data.local.PreferencesHelperImpl
import com.baseandroid.data.network.DataManager
import com.baseandroid.data.network.DataManagerImpl
import com.baseandroid.data.network.api.ApiHelper
import com.baseandroid.data.network.api.ApiHelperImpl
import com.baseandroid.di.*
import com.baseandroid.utils.BASE_URL
import com.baseandroid.utils.PREF_NAME
import com.baseandroid.utils.factory.IntentProviderFactory
import com.baseandroid.utils.factory.ViewModelProviderFactory
import com.baseandroid.utils.resource.ResourceProvider
import com.baseandroid.utils.resource.ResourceProviderImpl
import com.baseandroid.utils.rx.SchedulerProvider
import com.baseandroid.utils.rx.SchedulerProviderImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun provideContext(application: Application): Context

    @Module
    companion object {
        @JvmStatic
        @Provides
        @BaseUrl
        fun provideBaseUrl(): String = BASE_URL

        @JvmStatic
        @Provides
        @Singleton
        fun provideMoshi(): Gson = GsonBuilder().create()

        @JvmStatic
        @Provides
        @Singleton
        fun provideIntentFactory(application: Application): IntentProviderFactory {
            return IntentProviderFactory(application)
        }

        @JvmStatic
        @Provides
        @PrefName
        fun providePrefName(): String = PREF_NAME

        @JvmStatic
        @Provides
        @ListVertical
        fun provideLinearLayoutManagerVertical(context: Context): LinearLayoutManager {
            return LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }

        @JvmStatic
        @Provides
        @ListHorizontal
        fun provideLinearLayoutManagerHorizontal(context: Context): LinearLayoutManager {
            return LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }

        @JvmStatic
        @Provides
        @SpanCount
        fun provideGridLayoutManager(context: Context): GridLayoutManager {
            return GridLayoutManager(context, 2)
        }
    }


    @Binds
    @Singleton
    abstract fun providePreferencesHelper(preferencesHelper: PreferencesHelperImpl): PreferencesHelper

    @Binds
    @Singleton
    abstract fun provideViewModelFactory(viewModelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory

    @Binds
    @Singleton
    abstract fun provideDataManager(dataManager: DataManagerImpl): DataManager

    @Binds
    @Singleton
    abstract fun provideSchedulerProvider(schedulerProvider: SchedulerProviderImpl): SchedulerProvider

    @Binds
    @Singleton
    abstract fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper

    @Binds
    @Singleton
    abstract fun provideResourceProvider(resourceProvider: ResourceProviderImpl): ResourceProvider


}