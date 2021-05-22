package com.adityaikhbalm.libraries.ui.bottomnavigation

import android.content.Context
import android.util.AttributeSet
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener

class BottomNavigationCustom(
    context: Context,
    attrs: AttributeSet?
) : BottomNavigationView(context, attrs), OnNavigationItemSelectedListener {

    private val onNavigationItemSelectedListeners =
        mutableListOf<OnNavigationItemSelectedListener>()

    init {
        super.setOnNavigationItemSelectedListener(this)
    }

    override fun setOnNavigationItemSelectedListener(listener: OnNavigationItemSelectedListener?) {
        if (listener != null) addOnNavigationItemSelectedListener(listener)
    }

    private fun addOnNavigationItemSelectedListener(listener: OnNavigationItemSelectedListener) {
        onNavigationItemSelectedListeners.add(listener)
    }

    fun addOnNavigationItemSelectedListener(listener: (Int) -> Unit) {
        addOnNavigationItemSelectedListener(
            OnNavigationItemSelectedListener {
                for (i in 0 until menu.size()) if (menu.getItem(i) == it) listener(i)
                false
            }
        )
    }

    override fun onNavigationItemSelected(item: MenuItem) =
        onNavigationItemSelectedListeners
            .map { it.onNavigationItemSelected(item) }
            .fold(false) { acc, it -> acc || it }
}
