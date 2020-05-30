package com.ycyang.camera

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.ycyang.drawpractice.R
import com.ycyang.drawpractice.dp

private var BITMAP_SIZE = 200.dp
private val BITMAP_PADDING = 100.dp

class CameraView(context: Context, attributeSet: AttributeSet?) : View(context, attributeSet) {
    var resource = context.resources
    private val bitmap = getAvatar(BITMAP_SIZE.toInt())

    var paint = Paint()

    var camera = Camera()

    init {
        camera.rotateX(45f)
        camera.setLocation(0f, 0f, -6 * resource.displayMetrics.density)
    }

    private fun getAvatar(width: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resource, R.drawable.vavtor, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(resource, R.drawable.vavtor, options)
    }

    override fun onDraw(canvas: Canvas) {
//        BITMAP_SIZE = bitmap.width.toFloat()


        //下半部分
        canvas.save()
        canvas.translate(BITMAP_PADDING + BITMAP_SIZE / 2, BITMAP_PADDING + BITMAP_SIZE / 2)
        canvas.rotate(-20f)
        camera.applyToCanvas(canvas)
        canvas.clipRect(-BITMAP_SIZE, 0f, BITMAP_SIZE, BITMAP_SIZE)
        canvas.rotate(20f)
        canvas.translate(-BITMAP_PADDING - BITMAP_SIZE / 2, -BITMAP_PADDING - BITMAP_SIZE / 2)
        canvas.drawBitmap(bitmap, BITMAP_PADDING, BITMAP_PADDING, paint)
        canvas.restore()
        //上半部分
        canvas.save()
        canvas.translate(BITMAP_PADDING + BITMAP_SIZE / 2, BITMAP_PADDING + BITMAP_SIZE / 2)
        canvas.rotate(-20f)
        canvas.clipRect(-BITMAP_SIZE, -BITMAP_SIZE, BITMAP_SIZE, 0f)
        canvas.rotate(20f)
        canvas.translate(-BITMAP_PADDING - BITMAP_SIZE / 2, -BITMAP_PADDING - BITMAP_SIZE / 2)
        canvas.drawBitmap(bitmap, BITMAP_PADDING, BITMAP_PADDING, paint)
        canvas.restore()


    }
}