package com.ycyang.animator

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.ycyang.drawpractice.dp

private val CIRCLE_SIZE = 200.dp
private val CIRCLE_PADDING = 50.dp

class CircleView2(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    var paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val size = ((CIRCLE_PADDING + CIRCLE_SIZE / 2) * 2).toInt()
        val width = resolveSize(size, widthMeasureSpec)
        val height = resolveSize(size, heightMeasureSpec)
        setMeasuredDimension(width, height)
    }


    override fun onDraw(canvas: Canvas) {
        paint.apply {
            color = Color.BLUE
        }
        canvas.drawCircle(CIRCLE_PADDING + CIRCLE_SIZE / 2, CIRCLE_PADDING + CIRCLE_SIZE / 2, CIRCLE_SIZE / 2, paint)
    }
}