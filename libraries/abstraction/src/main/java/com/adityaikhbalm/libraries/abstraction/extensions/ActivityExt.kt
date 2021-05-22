package com.adityaikhbalm.libraries.abstraction.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.core.os.bundleOf

inline fun <reified T : Activity> Activity.startActivity() {
    startActivity(createIntent<T>())
}

inline fun <reified T : Activity> Context.createIntent() =
    Intent(this, T::class.java)

inline fun <reified T : Activity> Activity.startActivity(vararg extras: Pair<String, Any?>) {
    startActivity(createIntent<T>(*extras))
}

inline fun <reified T : Activity> Context.createIntent(vararg extras: Pair<String, Any?>) =
    Intent(this, T::class.java).apply { putExtras(bundleOf(*extras)) }
