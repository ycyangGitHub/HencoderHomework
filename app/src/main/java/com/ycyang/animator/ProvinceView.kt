package com.ycyang.animator

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.ycyang.drawpractice.dp

class ProvinceView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    var province = "北京市"
        set(value) {
            field = value
            invalidate()
//            val drawable = ColorDrawable()
//            drawable.toBitmap().toDrawable(resources)
        }
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 80.dp
        textAlign = Paint.Align.CENTER
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawText(province, width / 2f, height / 2f, paint)

    }
}