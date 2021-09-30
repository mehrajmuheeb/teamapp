package com.teamapp.data.network.api

import com.teamapp.data.model.Player
import io.reactivex.Observable
import retrofit2.http.GET

interface Api {

    @GET("players.json")
    fun getTeams(): Observable<Map<String, List<Player>>>
}