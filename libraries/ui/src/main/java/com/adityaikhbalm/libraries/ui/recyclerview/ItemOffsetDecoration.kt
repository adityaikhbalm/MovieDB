package com.adityaikhbalm.libraries.ui.recyclerview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.adityaikhbalm.libraries.utility.Utility.convertDpToPixel

class ItemOffsetDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(
            space.convertDpToPixel, 0, 0, space.convertDpToPixel
        )
    }
}
