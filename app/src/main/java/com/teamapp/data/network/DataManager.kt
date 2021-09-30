package com.teamapp.data.network

import com.teamapp.data.model.Player
import com.teamapp.utils.resource.ResourceProvider
import io.reactivex.Observable

interface DataManager {
    fun getTeams(): Observable<Map<String, List<Player>>>

    fun getResourceProvider(): ResourceProvider
}