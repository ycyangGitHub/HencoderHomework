package com.ycyang.text

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.ycyang.drawpractice.R
import com.ycyang.drawpractice.dp


private val IMAGE_SIZE = 50.dp
private val IMAGE_PADDING = 50.dp


class MutilText(context: Context, attr: AttributeSet?) : View(context, attr) {
    val text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur tristique urna tincidunt maximus viverra. Maecenas commodo pellentesque dolor ultrices porttitor. Vestibulum in arcu rhoncus, maximus ligula vel, consequat sem. Maecenas a quam libero. Praesent hendrerit ex lacus, ac feugiat nibh interdum et. Vestibulum in gravida neque. Morbi maximus scelerisque odio, vel pellentesque purus ultrices quis. Praesent eu turpis et metus venenatis maximus blandit sed magna. Sed imperdiet est semper urna laoreet congue. Praesent mattis magna sed est accumsan posuere. Morbi lobortis fermentum fringilla. Fusce sed ex tempus, venenatis odio ac, tempor metus."

    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 16.dp
    }
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 16.dp
    }

    val resouces = context.resources
    val bitmap = getAvatar(IMAGE_SIZE.toInt())
    var imageW = bitmap.width
    var fontMetric = Paint.FontMetrics()
    var measuredWidth = floatArrayOf(0f)
    override fun onDraw(canvas: Canvas) {
        //使用staticLayout对象绘制换行
//        val staticLayout = StaticLayout(text, textPaint, width, Layout.Alignment.ALIGN_NORMAL, 1f, 0f, false)
//        staticLayout.draw(canvas)
        var count: Int
        var start = 0

        var maxWidth = width.toFloat()
        textPaint.getFontMetrics(fontMetric)

        var veticalOffest = textPaint.fontSpacing
        canvas.drawBitmap(bitmap, width - imageW.toFloat(), IMAGE_PADDING, paint)
        while (start < text.length) {
            maxWidth = if (veticalOffest + fontMetric.bottom < IMAGE_PADDING || veticalOffest + fontMetric.top > IMAGE_PADDING + bitmap.height
            ) {
                width.toFloat()
            } else {
                width.toFloat() - bitmap.width
            }
            count = textPaint.breakText(text, start, text.length, true, maxWidth, measuredWidth)
            canvas.drawText(text, start, start + count, 0f, veticalOffest, paint)
            veticalOffest += textPaint.fontSpacing
            start += count
        }

    }

    fun getAvatar(width: Int): Bitmap {
        val option = BitmapFactory.Options()
        option.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resouces, R.drawable.vavtor)
        option.inJustDecodeBounds = false
        option.inDensity = option.outWidth
        option.inTargetDensity = width
        return BitmapFactory.decodeResource(resouces, R.drawable.vavtor, option)
    }
}

