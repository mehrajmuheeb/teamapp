package com.baseandroid.data.network.api

import android.content.Context
import retrofit2.Retrofit
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(var context: Context, retrofit: Retrofit): ApiHelper {
}