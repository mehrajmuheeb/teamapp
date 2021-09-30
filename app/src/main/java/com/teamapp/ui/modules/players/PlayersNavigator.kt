package com.teamapp.ui.modules.players

import com.teamapp.data.model.Player
import com.teamapp.ui.base.BaseNavigator
import java.util.ArrayList

interface PlayersNavigator : BaseNavigator {
    fun onSort(playerList: ArrayList<Player>)
}