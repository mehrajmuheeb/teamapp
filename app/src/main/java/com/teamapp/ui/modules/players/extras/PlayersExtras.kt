package com.teamapp.ui.modules.players.extras

import android.os.Bundle
import com.teamapp.data.model.Player

class PlayersExtras() {

    companion object {
        const val PLAYER_LIST = "orderID"

        inline fun playersExtras(block: Builder.() -> Unit) =
            Builder()
                .apply(block)
                .build()
    }

    class Builder {
        var player: ArrayList<Player> = arrayListOf()
        fun build(): Bundle {
            return Bundle().apply {
                putParcelableArrayList(PLAYER_LIST, player)
            }
        }
    }
}