package com.ycyang.text

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.ycyang.drawpractice.R
import com.ycyang.drawpractice.dp

private val circleRadius = 100.dp
private val centerX = 540
private val centerY = 960

class SportView(context: Context, attrs: AttributeSet?)
    : View(context, attrs) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val circlePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    val textPaint = Paint()
    val circlePath = Path()
    var textBound = Rect()
    val circleRect = RectF()


    init {
        paint.apply {
            color = Color.YELLOW
            style = Paint.Style.STROKE
            strokeWidth = 10.dp
            strokeCap = Paint.Cap.ROUND
        }
        circlePaint.apply {
            color = Color.GRAY
            style = Paint.Style.STROKE
            strokeWidth = 9.dp
            strokeCap = Paint.Cap.ROUND
        }
        textPaint.apply {
            style = Paint.Style.FILL
            color = Color.BLUE
            textAlign = Paint.Align.CENTER
            textSize = 80.dp
            typeface = ResourcesCompat.getFont(context, R.font.font)

        }
    }

    override fun onDraw(canvas: Canvas) {
        circleRect.left = width / 2 - circleRadius
        circleRect.top = height / 2 - circleRadius
        circleRect.right = width / 2 + circleRadius
        circleRect.bottom = height / 2 + circleRadius
        circlePath.addArc(circleRect, -70f, 240f)
        canvas.drawCircle(width / 2.toFloat(), height / 2.toFloat(), circleRadius, circlePaint)
        canvas.drawPath(circlePath, paint)
        textPaint.getTextBounds("abab", 0, "abab".length, textBound)
        canvas.drawText("abab", width / 2.toFloat(), height / 2.toFloat() - (textBound.top + textBound.bottom) / 2, textPaint)


    }

}

