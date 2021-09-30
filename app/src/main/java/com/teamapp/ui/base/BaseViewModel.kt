package com.teamapp.ui.base

import android.os.Bundle
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.teamapp.data.network.DataManager
import com.teamapp.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

abstract class BaseViewModel<N>(var dataManager: DataManager, var schedulerProvider: SchedulerProvider) : ViewModel() {
    lateinit var navigator: WeakReference<N>
    val isLoading = ObservableBoolean(false)

    val isIconVisible = ObservableBoolean(false)
    val title = ObservableField<String>("")

    val compositeDisposable: CompositeDisposable = CompositeDisposable()
    abstract fun setData(extras: Bundle)

    fun setNavigator(navigator: N) {
        this.navigator = WeakReference(navigator)
    }
    fun getNavigator() : N? = navigator.get()

    fun onBackButtonPress(view: View) {
        (getNavigator() as BaseNavigator).onBackPress()
    }
}