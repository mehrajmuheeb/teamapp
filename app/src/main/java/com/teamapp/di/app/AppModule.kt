package com.teamapp.di.app

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teamapp.data.local.PreferencesHelper
import com.teamapp.data.local.PreferencesHelperImpl
import com.teamapp.data.network.DataManager
import com.teamapp.data.network.DataManagerImpl
import com.teamapp.data.network.api.ApiHelper
import com.teamapp.data.network.api.ApiHelperImpl
import com.teamapp.di.*
import com.teamapp.utils.BASE_URL
import com.teamapp.utils.PREF_NAME
import com.teamapp.utils.factory.IntentProviderFactory
import com.teamapp.utils.factory.ViewModelProviderFactory
import com.teamapp.utils.resource.ResourceProvider
import com.teamapp.utils.resource.ResourceProviderImpl
import com.teamapp.utils.rx.SchedulerProvider
import com.teamapp.utils.rx.SchedulerProviderImpl
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