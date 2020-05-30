package com.ycyang.drawpractice

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

private val IMAGE_WIDTH = 200f
private val IMAGE_PADDING = 20f
private val paint: Paint = Paint()
private val xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
val bound = RectF(300f, 300f, 710f, 710f)

class PotritView constructor(context: Context, attr: AttributeSet) : View(context, attr) {
    init {
        setLayerType(LAYER_TYPE_SOFTWARE,paint)
    }
    override fun onDraw(canvas: Canvas) {
//        var count = canvas.saveLayer(bound, null)
        canvas.drawCircle(500f, 500f, 200f, paint)
        paint.xfermode = xfermode
        canvas.drawBitmap(getAvatar(115), 300f, 300f, paint)
//        canvas.restoreToCount(count)
    }

    private fun getAvatar(width: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.vavtor, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(resources, R.drawable.vavtor, options)
    }

}

