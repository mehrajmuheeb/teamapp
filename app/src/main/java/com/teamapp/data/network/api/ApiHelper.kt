package com.teamapp.data.network.api

import com.teamapp.data.model.Player
import io.reactivex.Observable

interface ApiHelper {

    fun getTeams(): Observable<Map<String, List<Player>>>
}