package com.baseandroid.ui.base

import android.os.Bundle
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.baseandroid.data.network.DataManager
import com.baseandroid.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

abstract class BaseViewModel<N>(var dataManager: DataManager, var schedulerProvider: SchedulerProvider) : ViewModel() {
    lateinit var navigator: WeakReference<N>
    val isLoading = ObservableBoolean(false)

    val compositeDisposable: CompositeDisposable = CompositeDisposable()
    abstract fun setData(extras: Bundle)

    fun setNavigator(navigator: N) {
        this.navigator = WeakReference(navigator)
    }
    fun getNavigator() : N? = navigator.get()
}