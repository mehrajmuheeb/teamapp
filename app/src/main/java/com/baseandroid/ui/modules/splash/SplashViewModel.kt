package com.baseandroid.ui.modules.splash

import android.os.Bundle
import com.baseandroid.data.network.DataManager
import com.baseandroid.ui.base.BaseViewModel
import com.baseandroid.utils.rx.SchedulerProvider

class SplashViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider):
    BaseViewModel<SplashNavigator>(dataManager, schedulerProvider) {

    override fun setData(extras: Bundle) {

    }
}