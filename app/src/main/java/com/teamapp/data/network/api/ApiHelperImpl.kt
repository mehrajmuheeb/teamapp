package com.teamapp.data.network.api

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.teamapp.R
import com.teamapp.data.model.Player
import com.teamapp.utils.ApiFailException
import com.teamapp.utils.CONNECTION_ERROR
import com.teamapp.utils.NoNetworkException
import io.reactivex.Observable
import retrofit2.Retrofit
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(var context: Context, retrofit: Retrofit): ApiHelper {
    var api: Api = retrofit.create(Api::class.java)

    override fun getTeams(): Observable<Map<String, List<Player>>> {
        return handleResponse(api.getTeams())
    }

    private fun handleResponse(response: Observable<Map<String, List<Player>>>): Observable<Map<String, List<Player>>> {
        return isInternetOn()
            .doOnNext(this::handleNetworkStatus)
            .switchMap {
                response
                    .doOnError(this::onHttpException)
                    .map {
                        it
                    }

            }
    }

    @Throws(ApiFailException::class)
    private fun onHttpException(throwable: Throwable) {
        Log.i("Error", throwable.message)
    }

    private fun handleNetworkStatus(networkStatus: Boolean) {
        if (!networkStatus) {
            throw NoNetworkException(
                CONNECTION_ERROR,
                R.string.error_no_internet_connection
            )
        }
    }

    fun isInternetOn(): Observable<Boolean> {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return Observable.just(manager?.activeNetworkInfo?.isConnected ?: false)
    }


}