package com.adityaikhbalm.libraries.abstraction.extensions

import android.content.Context
import android.graphics.PorterDuff
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import com.adityaikhbalm.libraries.ui.R
import com.adityaikhbalm.libraries.utility.Utility.getMyColor

interface BaseSearch {
    var menuFocus: Boolean
    var searchText: String?
    var searchSubmit: Boolean
}

fun BaseSearch.setMenu(
    context: Context,
    searchMenuItem: MenuItem,
    paging: () -> Unit,
    search: () -> Unit
) {
    val searchView = searchMenuItem.actionView as SearchView
    if (menuFocus || !searchText.isNullOrEmpty()) searchMenuItem.expandActionView()

    searchView.run {
        maxWidth = Int.MAX_VALUE
        queryHint = context.getString(R.string.search)
        findViewById<View>(androidx.appcompat.R.id.search_plate).background = null
        findViewById<ImageView>(androidx.appcompat.R.id.search_close_btn).setColorFilter(
            context.getMyColor(R.color.color_selected),
            PorterDuff.Mode.SRC_IN
        )

        findViewById<ImageView>(androidx.appcompat.R.id.search_button).setColorFilter(
            context.getMyColor(R.color.color_selected),
            PorterDuff.Mode.SRC_IN
        )

        val searchEditText = findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
        searchEditText.setTextColor(context.getMyColor(R.color.medium_white))
        searchEditText.setHintTextColor(context.getMyColor(R.color.medium_white))

        searchEditText.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            menuFocus = hasFocus
        }

        searchView.setQuery(searchText, false)

        setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchSubmit = true
                searchText = query
                clearFocus()
                if (query != null) search.invoke()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchText = newText
                return false
            }
        })
    }

    searchMenuItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
        override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
            return true
        }

        override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
            if (!searchText.isNullOrEmpty() && searchSubmit) {
                paging.invoke()
                searchSubmit = false
            }
            return true
        }
    })
}
