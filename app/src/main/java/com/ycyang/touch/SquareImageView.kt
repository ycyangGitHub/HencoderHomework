package com.ycyang.touch

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import java.lang.Integer.min

class SquareImageView(context: Context?, attrs: AttributeSet?) : ImageView(context, attrs) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val size = min(measuredHeight, measuredWidth)
        setMeasuredDimension(size, size)
    }
//    override fun layout(l: Int, t: Int, r: Int, b: Int) {
//        var width = r - l
//        var height = b - t
//        var w = max(width, height)
//        super.layout(l, t, l + w, t + w)
//    }
}