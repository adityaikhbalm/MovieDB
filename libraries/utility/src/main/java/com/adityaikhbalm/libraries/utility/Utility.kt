package com.adityaikhbalm.libraries.utility

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible

object Utility {
    private val metrics = Resources.getSystem().displayMetrics

    val Int.convertDpToPixel
        get() = (this * metrics.density).toInt()

    val Int.convertPixelToDp
        get() = (this / metrics.density).toInt()

    fun Context.getMyColor(color: Int) = ContextCompat.getColor(this, color)

    fun Context.colorStateList(color: Int) = ContextCompat.getColorStateList(this, color)

    fun View.show() {
        this.isVisible = true
    }

    fun View.hide() {
        this.isVisible = false
    }

    fun calculateNoOfColumns(columnWidthDp: Float): Int {
        val screenWidthDp = metrics.widthPixels / metrics.density
        return (screenWidthDp / columnWidthDp + 0.5).toInt()
    }

    @SuppressLint("ShowToast")
    fun Context.toastShow(message: Any?) {
        var toast: Toast? = null
        if (message is @StringRes Int) {
            toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        } else if (message is String) {
            toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        }
        toast?.show()
    }
}
