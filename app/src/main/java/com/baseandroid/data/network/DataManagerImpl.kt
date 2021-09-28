package com.baseandroid.data.network

import com.baseandroid.data.local.PreferencesHelper
import com.baseandroid.data.network.api.ApiHelper
import com.baseandroid.utils.resource.ResourceProvider
import javax.inject.Inject

class DataManagerImpl @Inject constructor(var apiHelper: ApiHelper,
                                          var preferencesHelper: PreferencesHelper,
                                          var resourceProviders: ResourceProvider) : DataManager {
}