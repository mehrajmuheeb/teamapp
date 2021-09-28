package com.baseandroid.ui.bindingAdapters

import androidx.databinding.BindingAdapter
import com.baseandroid.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout

class TextInputBindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("app:showError")
        fun showErrorMessage(view: TextInputLayout, message: String) {
            view.error = message
        }

        @JvmStatic
        @BindingAdapter("app:isActive")
        fun toggleButtonColor(button: MaterialButton, isActive: Boolean) {
            if (isActive)
                button.setStrokeColorResource(R.color.colorPrimary)
            else
                button.setStrokeColorResource(R.color.colorAccent)
        }
    }
}