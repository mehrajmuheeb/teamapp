package com.baseandroid.ui.bindingAdapters

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("app:imageBitmap")
fun loadImage(iv: ImageView, bitmap: Bitmap?) {
    bitmap ?: return
    iv.setImageBitmap(bitmap)
}