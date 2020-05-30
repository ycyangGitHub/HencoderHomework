package com.ycyang.touch

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.ycyang.drawpractice.dp
import com.ycyang.drawpractice.getAvatar

class MutilTouchView1(context: Context, attrs: AttributeSet?) : View(context, attrs) {
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
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                donwX = event.x
                donwY = event.y
                stopX = offsetX
                stopY = offsetY
                traceId = event.getPointerId(0)
            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                traceId = event.getPointerId(event.actionIndex)
                donwX = event.getX(event.actionIndex)
                donwY = event.getY(event.actionIndex)
                stopX = offsetX
                stopY = offsetY
            }
            MotionEvent.ACTION_POINTER_UP -> {
                var index = event.actionIndex
                var pointId = event.getPointerId(index)
                if (pointId == traceId) {
                    val nextTrace = if (index == event.pointerCount-1) {
                        event.pointerCount - 2
                    } else {
                        event.pointerCount - 1
                    }
                    traceId = event.getPointerId(nextTrace)
                    donwX = event.getX(nextTrace)
                    donwY = event.getY(nextTrace)
                    stopX = offsetX
                    stopY = offsetY

                }


            }
            MotionEvent.ACTION_MOVE -> {
                offsetX = stopX + event.getX(event.findPointerIndex(traceId)) - donwX
                offsetY = stopY + event.getY(event.findPointerIndex(traceId)) - donwY
                invalidate()
            }

        }
        return true
    }
}