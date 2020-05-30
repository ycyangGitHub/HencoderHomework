package com.ycyang.touch

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.SparseArray
import android.view.MotionEvent
import android.view.View
import com.ycyang.drawpractice.dp

class MutilTouchView3(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var paths = SparseArray<Path>()

    init {
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 4.dp
        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeJoin = Paint.Join.ROUND
    }

    override fun onDraw(canvas: Canvas) {
        for (i in 0 until paths.size()) {
            val path = paths.valueAt(i)
            canvas.drawPath(path, paint)

        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                val path = Path()
                var actionIndex = event.actionIndex
                paths.append(event.getPointerId(actionIndex), path)
                path.moveTo(event.getX(actionIndex), event.getY(actionIndex))
                invalidate()
            }
            MotionEvent.ACTION_MOVE -> {
                for (i in 0 until paths.size()) {
                    val pointerId = event.getPointerId(i)
                    val path = paths.get(pointerId)
                    path.lineTo(event.getX(i), event.getY(i))
                }
//                var actionIndex = event.actionIndex
//                var pointId = event.getPointerId(actionIndex)
//                var path = paths.get(pointId)
//                path.lineTo(event.getX(actionIndex), event.getY(actionIndex))
                invalidate()
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP -> {
                var actionIndex = event.actionIndex
                var actionId = event.getPointerId(actionIndex)
                var path = paths.get(actionId)
                path.reset()
                paths.remove(actionId)
                invalidate()
            }
        }

        return true
    }


}