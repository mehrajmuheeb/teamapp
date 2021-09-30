package com.teamapp.ui.bindingAdapters

import android.app.Activity
import android.os.Handler
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.google.android.material.button.MaterialButton


fun Activity.halt(seconds: Long, function: () -> Unit) {
    Handler().postDelayed(function, seconds)
}

@BindingAdapter("app:btnTint")
fun changeButtonTint(btn: MaterialButton, color: Int) {
    val mColor = ContextCompat.getColor(btn.context, color)
    btn.setBackgroundColor(mColor)
}