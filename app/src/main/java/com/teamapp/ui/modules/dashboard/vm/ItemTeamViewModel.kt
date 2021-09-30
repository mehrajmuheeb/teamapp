package com.teamapp.ui.modules.dashboard.vm

import android.view.View
import androidx.lifecycle.ViewModel

class ItemTeamViewModel(val name: String, val listener: (index: String) -> Unit) : ViewModel() {

    fun onItemClick(view: View) {
        listener(name)
    }
}