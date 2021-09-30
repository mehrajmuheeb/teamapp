package com.teamapp.ui.modules.dashboard

import androidx.lifecycle.Observer
import com.teamapp.R
import com.teamapp.data.model.Player
import com.teamapp.databinding.ActivityMainBinding
import com.teamapp.ui.base.BaseActivity
import com.teamapp.ui.modules.dashboard.adapter.TeamAdapter
import com.teamapp.ui.modules.dashboard.vm.MainViewModel
import com.teamapp.ui.modules.players.PlayersActivity
import com.teamapp.ui.modules.players.extras.PlayersExtras


/**
 * @description:
 * @author:
 * @created_on: 10:53 AM 9/29/2021
 * @param:
 */

class MainActivity : BaseActivity<MainNavigator, MainViewModel, ActivityMainBinding>(),
    MainNavigator {

    lateinit var adapter: TeamAdapter

    override fun getLayout(): Int = R.layout.activity_main

    override fun init() {
        bind(MainViewModel::class.java)

        viewModel.teamList.observe(this, Observer {
            adapter.addItems(it)
            adapter.notifyDataSetChanged()
        })
    }

    override fun onViewModelCreated(viewModel: MainViewModel) {
        viewModel.setNavigator(this)
        viewModel.getTeamsList()
    }

    override fun onViewBound(viewDataBinding: ActivityMainBinding) {
        adapter = TeamAdapter(mutableListOf()) {
            viewModel.findPlayers(it)
        }
        viewDataBinding.rvTeam.adapter = adapter
    }

    override fun navigateToPlayers(players: List<Player>?) {
        var playerList = arrayListOf<Player>()
        players?.map {
            playerList.add(it)
        }
        startActivity(
            intentProviderFactory.create(
                PlayersActivity::class.java,
                PlayersExtras.playersExtras {
                    player = playerList
                },
                0
            )
        )
    }
}