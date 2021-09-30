package com.teamapp.ui.base

interface BaseNavigator {
    fun onBackPress()
    fun onError(t: Throwable, showAction: Boolean = true, function: () -> Unit = {})
}