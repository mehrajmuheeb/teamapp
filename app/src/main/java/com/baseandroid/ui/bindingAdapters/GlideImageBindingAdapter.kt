package com.baseandroid.ui.bindingAdapters

import android.net.Uri
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class GlideImageBindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("app:setImageRes")
        fun setUserImage(imgView: ImageView, url: String) {
            Glide
                .with(imgView)
                .load(Uri.parse(url))
                .placeholder(ContextCompat.getDrawable(imgView.context, android.R.drawable.btn_minus))
                .error(ContextCompat.getDrawable(imgView.context, android.R.drawable.btn_minus))
                .into(imgView)
        }
    }
}