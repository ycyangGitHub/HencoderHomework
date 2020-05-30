package com.ycyang.animator

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.ycyang.drawpractice.R
import com.ycyang.drawpractice.dp

class FlipView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    var IMAGE_SIZE = 0.dp
    var IMAGE_PADDING = 100.dp
    var camera = Camera()
    var paint = Paint()
    var bottomFlip = 0.dp
        set(value) {
            field = value
            invalidate()
        }
    var rotateY = 0.dp
        set(value) {
            field = value
            invalidate()
        }
    var rotateX = 0.dp
        set(value) {
            field = value
            invalidate()
        }
    var bitmap: Bitmap

    init {
        bitmap = getAvatar(200.dp.toInt())
        IMAGE_SIZE = bitmap.width.toFloat()
        camera.setLocation(0f, 0f,-6* resources.displayMetrics.density)
    }

    override fun onDraw(canvas: Canvas) {
        //上一半
        canvas.save()
        canvas.translate(IMAGE_PADDING + IMAGE_SIZE / 2, IMAGE_PADDING + IMAGE_SIZE / 2)
        canvas.rotate(rotateY)
        camera.save()
        camera.rotateX(rotateX)
        camera.applyToCanvas(canvas)
        camera.restore()
        canvas.clipRect(-IMAGE_SIZE ,-IMAGE_SIZE , IMAGE_SIZE ,0f )
        canvas.rotate(-rotateY)
        canvas.translate(-(IMAGE_PADDING + IMAGE_SIZE / 2), -(IMAGE_PADDING + IMAGE_SIZE / 2))
        canvas.drawBitmap(bitmap, IMAGE_PADDING, IMAGE_PADDING, paint)
        canvas.restore()
//        //下一半
        canvas.save()
        canvas.translate(IMAGE_PADDING + IMAGE_SIZE / 2, IMAGE_PADDING + IMAGE_SIZE / 2)
        canvas.rotate(rotateY)
        camera.save()
        camera.rotateX(bottomFlip)
        camera.applyToCanvas(canvas)
        camera.restore()
        canvas.clipRect(-IMAGE_SIZE , 0f, IMAGE_SIZE , IMAGE_SIZE )
        canvas.rotate(-rotateY)
        canvas.translate(-(IMAGE_PADDING + IMAGE_SIZE / 2), -(IMAGE_PADDING + IMAGE_SIZE / 2))
        canvas.drawBitmap(bitmap, IMAGE_PADDING, IMAGE_PADDING, paint)
        canvas.restore()
    }

    private fun getAvatar(width: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources,
                R.drawable.vavtor, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(resources,
                R.drawable.vavtor, options)
    }
}