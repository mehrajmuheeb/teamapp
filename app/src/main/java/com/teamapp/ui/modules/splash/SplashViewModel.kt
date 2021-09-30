package com.teamapp.ui.modules.splash

import android.os.Bundle
import com.teamapp.data.network.DataManager
import com.teamapp.ui.base.BaseViewModel
import com.teamapp.utils.rx.SchedulerProvider

class SplashViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider):
    BaseViewModel<SplashNavigator>(dataManager, schedulerProvider) {

    override fun setData(extras: Bundle) {

    }

    init {
    }
}