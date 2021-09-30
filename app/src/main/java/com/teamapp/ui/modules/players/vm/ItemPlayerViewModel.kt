package com.teamapp.ui.modules.players.vm

import androidx.lifecycle.ViewModel
import com.teamapp.data.model.Player

class ItemPlayerViewModel(val player: Player) : ViewModel() {
    var name: String = if (player.captain) {
        "${player.name} (C)"
    } else {
        player.name
    }

    init {

    }
}