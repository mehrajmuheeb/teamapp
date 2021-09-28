package com.baseandroid.utils

import android.app.Activity
import android.graphics.Color
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

class NoNetworkException(message: String?, @field:StringRes @param:StringRes val messageRes: Int) :
    Exception(message)

class ApiFailException : java.lang.Exception {
    var statusCode = 0
        private set
    @StringRes
    var messageRes = 0
        private set

    constructor() {}
    constructor(statusCode: Int, message: String?, @StringRes messageRes: Int) : super(
        message
    ) {
        this.statusCode = statusCode
        this.messageRes = messageRes
    }

}

fun Activity.showError(message: String,
                       actionName: String = "Retry",
                       showAction: Boolean = true,
                       function: () -> Unit = {}) {
//    val duration = if (showAction) Snackbar.LENGTH_INDEFINITE else Snackbar.LENGTH_LONG
    val duration = Snackbar.LENGTH_LONG
    Snackbar.make(findViewById(android.R.id.content), message, duration).apply {
        setBackgroundTint(Color.RED)
        if (showAction) {
            setAction(actionName) {
                function()
            }
        }
    }.show()
}

val httpErrorMap = mutableMapOf<Int, String>().apply {
    put(500,"Something went wrong." )
    put(401, "Your session has expired. Please sign in again.")
    put(405, "Invalid request URI or Method.")
    put(404, "The requested resource was not found.")
    put(503, "The server is under maintenance mode, please try after few minutes later.")
    put(400, "Please provide valid encryption string.")
    put(400, "something went wrong in push notification.")
}