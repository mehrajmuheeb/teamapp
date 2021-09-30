package com.teamapp.ui.modules.players

import androidx.lifecycle.Observer
import com.teamapp.R
import com.teamapp.data.model.Player
import com.teamapp.databinding.ActivityPlayersBinding
import com.teamapp.ui.base.BaseActivity
import com.teamapp.ui.modules.players.adapter.PlayersAdapter
import kotlinx.android.synthetic.main.activity_players.*
import java.util.ArrayList


/**
 * @description:
 * @author:
 * @created_on: 10:20 AM 9/30/2021
 * @param:
 */

class PlayersActivity : BaseActivity<PlayersNavigator, PlayersViewModel, ActivityPlayersBinding>(),
    PlayersNavigator {

    lateinit var adapter: PlayersAdapter

    override fun getLayout(): Int = R.layout.activity_players

    override fun init() {
        bind(PlayersViewModel::class.java)
    }

    override fun onViewModelCreated(viewModel: PlayersViewModel) {
        viewModel.setNavigator(this)

        viewModel.playersList.observe(this, Observer {
            adapter.addItems(it)
        })
    }

    override fun onViewBound(viewDataBinding: ActivityPlayersBinding) {
        adapter = PlayersAdapter(arrayListOf())
        viewDataBinding.rvPlayers.adapter = adapter
    }

    override fun onSort(playerList: ArrayList<Player>) {
        adapter.addItems(playerList)
    }
}