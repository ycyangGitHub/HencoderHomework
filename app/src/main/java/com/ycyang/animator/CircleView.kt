package com.ycyang.animator

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.ycyang.drawpractice.dp

class CircleView(context: Context, attributeSet: AttributeSet?) : View(context, attributeSet) {
    var radius = 50.dp
        set(value) {
            field = value.dp
            invalidate()
        }
    var paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        paint.color = Color.parseColor("#00796B")
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(width / 2f, height / 2f, radius, paint)
    }
}