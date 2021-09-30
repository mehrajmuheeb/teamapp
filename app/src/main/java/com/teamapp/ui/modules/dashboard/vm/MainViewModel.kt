package com.teamapp.ui.modules.dashboard.vm

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.teamapp.R
import com.teamapp.data.model.Player
import com.teamapp.data.network.DataManager
import com.teamapp.ui.base.BaseViewModel
import com.teamapp.ui.modules.dashboard.MainNavigator
import com.teamapp.utils.rx.SchedulerProvider

class MainViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
    BaseViewModel<MainNavigator>(dataManager, schedulerProvider) {

    lateinit var teamResponse: Map<String, List<Player>>
    val _teamList = MutableLiveData<List<String>>()
    val teamList: LiveData<List<String>>
    get() = _teamList

    override fun setData(extras: Bundle) {

    }

    init {
        title.set(dataManager.getResourceProvider().getString(R.string.team))
        isIconVisible.set(false)
    }

    fun getTeamsList() {
        if (isLoading.get()) return
        isLoading.set(true)

        compositeDisposable.addAll(
            dataManager.getTeams()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(this::onFetchSuccess, this::onFetchError)
        )
    }

    fun onFetchSuccess(teams: Map<String, List<Player>>) {
        isLoading.set(false)
        teamResponse = teams
       _teamList.value = teams.keys.toList()

    }

    fun onFetchError(t: Throwable) {
        isLoading.set(false)
        getNavigator()?.onError(t, true) {
            getTeamsList()
        }
    }

    fun findPlayers(key: String) {
        getNavigator()?.navigateToPlayers(teamResponse[key])
    }

}