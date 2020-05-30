package com.ycyang.touch

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.ycyang.drawpractice.dp
import com.ycyang.drawpractice.getAvatar

class MutilTouchView2(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    var bitmap = getAvatar(resources, 200.dp.toInt())
    var offsetX = 0f
    var offsetY = 0f
    var stopX = 0f
    var stopY = 0f
    var donwX = 0f
    var donwY = 0f
    var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var traceId = 0

    override fun onDraw(canvas: Canvas) {
//        canvas.translate(offsetX, offsetY)
        canvas.drawBitmap(bitmap, offsetX, offsetY, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val foucsX: Float
        val foucsY: Float
        var sumX = 0f
        var sumY = 0f
        var pointCount = event.pointerCount
        val isPointerUp = event.actionMasked == MotionEvent.ACTION_POINTER_UP
        for (i in 0 until pointCount) {
            if (i != event.actionIndex || !isPointerUp) {
                sumX += event.getX(i)
                sumY += event.getY(i)
            }

        }
        if (isPointerUp) {
            pointCount--
        }


        foucsX = sumX / pointCount
        foucsY = sumY / pointCount
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN, MotionEvent.ACTION_POINTER_UP -> {
                donwX = foucsX
                donwY = foucsY
                stopX = offsetX
                stopY = offsetY
            }

            MotionEvent.ACTION_MOVE -> {
                offsetX = stopX + foucsX - donwX
                offsetY = stopY + foucsY - donwY
                invalidate()
            }

        }
        return true
    }
}