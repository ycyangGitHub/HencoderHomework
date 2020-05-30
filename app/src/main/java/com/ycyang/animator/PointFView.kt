package com.ycyang.animator

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.ycyang.drawpractice.dp

class PointFView(context: Context, attributeSet: AttributeSet?) : View(context, attributeSet) {
    var skipPoint = PointF(0f, 0f)
        set(value) {
            field = value
            invalidate()
        }
    var paint = Paint()
    override fun onDraw(canvas: Canvas) {
        Log.e("yyyyy", skipPoint.x.toString())
        canvas.drawCircle(skipPoint.x, skipPoint.y, 20.dp, paint)
    }

}