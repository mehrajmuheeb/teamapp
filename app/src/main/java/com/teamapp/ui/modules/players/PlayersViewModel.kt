package com.teamapp.ui.modules.players

import android.os.Bundle
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.teamapp.R
import com.teamapp.data.model.Player
import com.teamapp.data.network.DataManager
import com.teamapp.ui.base.BaseViewModel
import com.teamapp.ui.modules.players.extras.PlayersExtras
import com.teamapp.utils.rx.SchedulerProvider
import java.util.*

class PlayersViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
    BaseViewModel<PlayersNavigator>(dataManager, schedulerProvider) {

    var playerList = ArrayList<Player>()

    private val _playersList = MutableLiveData<ArrayList<Player>>()
    val playersList: LiveData<ArrayList<Player>>
        get() = _playersList

    var btnFirstTint = ObservableField<Int>(R.color.blue)
    var btnSecondTint = ObservableField<Int>(R.color.blue)

    override fun setData(extras: Bundle) {
        playerList = extras.getParcelableArrayList(PlayersExtras.PLAYER_LIST) ?: ArrayList()
        _playersList.value = playerList
    }

    init {
        title.set(dataManager.getResourceProvider().getString(R.string.players))
        isIconVisible.set(true)
    }


    fun onActionClick(view: View) {

        when (view.id) {
            R.id.btn_first_sort -> {
                playerList.sortBy {
                    it.name
                }
                getNavigator()?.onSort(playerList)

                btnFirstTint.set(R.color.blue_active)
                btnSecondTint.set(R.color.blue)
            }

            R.id.btn_second_sort -> {
                playerList.sortBy {
                    val spliter = it.name.split(" ")
                    if (spliter.size > 1)
                        spliter[1]
                    else
                        spliter[0]
                }
                getNavigator()?.onSort(playerList)
                btnFirstTint.set(R.color.blue)
                btnSecondTint.set(R.color.blue_active)
            }
        }

    }

}