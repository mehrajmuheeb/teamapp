package com.teamapp.data.network

import com.teamapp.data.local.PreferencesHelper
import com.teamapp.data.model.Player
import com.teamapp.data.network.api.ApiHelper
import com.teamapp.utils.resource.ResourceProvider
import io.reactivex.Observable
import javax.inject.Inject

class DataManagerImpl @Inject constructor(var apiHelper: ApiHelper,
                                          var preferencesHelper: PreferencesHelper,
                                          var resourceProviders: ResourceProvider) : DataManager {
    override fun getTeams(): Observable<Map<String, List<Player>>> {
        return apiHelper.getTeams()
    }

    override fun getResourceProvider(): ResourceProvider = resourceProviders
}