package com.teamapp.ui.modules.dashboard

import com.teamapp.data.model.Player
import com.teamapp.ui.base.BaseNavigator

interface MainNavigator : BaseNavigator {

    fun navigateToPlayers(players: List<Player>?)
}