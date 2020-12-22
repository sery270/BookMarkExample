package com.example.bookmark.util

import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class ItemDecoration: RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val pos  = parent.getChildAdapterPosition(view)
        if(pos == 0)
            outRect.top = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                20F, view.context.resources.displayMetrics
            ).toInt()


    }

}