package com.ycyang.touch

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.OverScroller
import android.widget.Toast
import androidx.core.animation.doOnEnd
import androidx.core.view.GestureDetectorCompat
import com.ycyang.drawpractice.dp
import com.ycyang.drawpractice.getAvatar
import java.lang.Float.max
import java.lang.Float.min

private val IMAGE_SIZE = 200.dp.toInt()
private const val EXTRA_SCALE_FACTOR = 1.5f


class ScalableImageView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val bitmap = getAvatar(resources, IMAGE_SIZE)
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var originalxOffset = 0.0f
    var originalyOffest = 0.0f
    var extraXOffset = 0.0f
    var extraYOffset = 0.0f
    var smallScale = 0.0f
    var bigScale = 0.0f
    var big = false
    var henGestureScaleDetector = ScaleGestureDetector(context, HenScaleGestureListener())
    var fraction = 0f
        set(value) {
            field = value
            invalidate()
        }
    var scaleAnimator = ObjectAnimator.ofFloat(this, "currentScale", smallScale, bigScale).apply {
        doOnEnd {
            if (!big) {
                extraXOffset = 0f
                extraYOffset = 0f
            }
        }
    }
    var currentScale = 0f
        set(value) {
            field = value
            invalidate()
        }

    var hencorderGesture = SelfGestorLisener()
    var flingRunnable = FlingRunnable()

    val gestureDetector = GestureDetectorCompat(context, hencorderGesture)
    val scroller = OverScroller(context)


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var result = henGestureScaleDetector.onTouchEvent(event)
        if (!henGestureScaleDetector.isInProgress) {
            return gestureDetector.onTouchEvent(event)

        }
        return result
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        originalxOffset = (width - bitmap.width) / 2.toFloat()
        originalyOffest = (height - bitmap.height) / 2.toFloat()

        bigScale = max(width / bitmap.width.toFloat(), height / bitmap.height.toFloat()) * EXTRA_SCALE_FACTOR
        smallScale = min(width / bitmap.width.toFloat(), height / bitmap.height.toFloat())

        currentScale = smallScale

        scaleAnimator.setFloatValues(smallScale, bigScale)


    }

    override fun onDraw(canvas: Canvas) {
//        if (big) {
//            canvas.scale(bigScale, bigScale, width / 2.toFloat(), height / 2.toFloat())
//        } else {
//            canvas.scale(smallScale, smallScale, width / 2.toFloat(), height / 2.toFloat())
//
//        }
        super.onDraw(canvas)
        fraction = (currentScale - smallScale) / (bigScale - smallScale)
        canvas.translate(extraXOffset * fraction, extraYOffset * fraction)
        canvas.scale(currentScale, currentScale, width / 2.toFloat(), height / 2.toFloat())

        canvas.drawBitmap(bitmap, originalxOffset, originalyOffest, paint)
    }


    private fun fixOffsets() {
        extraXOffset = kotlin.math.min(extraXOffset, (bitmap.width * bigScale - width) / 2)
        extraXOffset = kotlin.math.max(extraXOffset, -(bitmap.width * bigScale - width) / 2)
        extraYOffset = kotlin.math.min(extraYOffset, (bitmap.height * bigScale - height) / 2)
        extraYOffset = kotlin.math.max(extraYOffset, -(bitmap.height * bigScale - height) / 2)
    }


    inner class SelfGestorLisener : SimpleOnGestureListener() {
        override fun onShowPress(e: MotionEvent?) {
            Toast.makeText(context, "onShowPress", Toast.LENGTH_LONG).show()
        }

        override fun onSingleTapUp(e: MotionEvent?): Boolean {
            Toast.makeText(context, "singleTap", Toast.LENGTH_LONG).show()
            return true
        }

        override fun onDown(e: MotionEvent?): Boolean {
            return true
        }

        override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
            if (big) {
                scroller.fling(extraXOffset.toInt(), extraYOffset.toInt(), velocityX.toInt(), velocityY.toInt(),
                        (-(bitmap.width * bigScale - width) / 2).toInt(),
                        ((bitmap.width * bigScale - width) / 2).toInt(),
                        (-(bitmap.height * bigScale - height) / 2).toInt(),
                        ((bitmap.height * bigScale - height) / 2).toInt()
                )
                postOnAnimation(flingRunnable)
            }
            return true
        }


        override fun onScroll(downEvent: MotionEvent, currentEvent: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
            if (big) {
                extraXOffset -= distanceX
                extraXOffset = min(extraXOffset, (bitmap.width * bigScale - width) / 2)
                extraXOffset = max(extraXOffset, -(bitmap.width * bigScale - width) / 2)

                extraYOffset -= distanceY
                extraYOffset = min(extraYOffset, (bitmap.height * bigScale - height) / 2)
                extraYOffset = max(extraYOffset, -(bitmap.height * bigScale - height) / 2)
                invalidate()
            }
            return true
        }

        override fun onLongPress(e: MotionEvent?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }


        override fun onDoubleTap(e: MotionEvent): Boolean {
            big = !big
            if (big) {
                extraXOffset = (e.x - width / 2f) * (1 - bigScale / smallScale)
                extraYOffset = (e.y - height / 2f) * (1 - bigScale / smallScale)
                fixOffsets()
                scaleAnimator.start()
            } else {
                scaleAnimator.reverse()
            }
            return true
        }

        override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
            return true
        }

        override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
           return true
        }

    }

    inner class FlingRunnable : Runnable {
        override fun run() {
            if (scroller.computeScrollOffset()) {
                extraXOffset = scroller.currX.toFloat()
                extraYOffset = scroller.currY.toFloat()
                invalidate()
                postOnAnimation(this)
            }
        }

    }

    inner class HenScaleGestureListener : ScaleGestureDetector.OnScaleGestureListener {
        override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {

            extraXOffset = (detector.focusX - width / 2f) * (1 - bigScale / smallScale)
            extraYOffset = (detector.focusY - height / 2f) * (1 - bigScale / smallScale)
            return true
        }

        override fun onScaleEnd(detector: ScaleGestureDetector?) {
        }

        override fun onScale(detector: ScaleGestureDetector): Boolean {
            val tempCurrentScale = currentScale * detector.scaleFactor
            if (tempCurrentScale < smallScale || tempCurrentScale > bigScale) {
                return false
            } else {
                currentScale *= detector.scaleFactor
                //currentScale = currentScale.coerceAtLeast(smallScale).coerceAtMost(bigScale)
                return true
            }

        }

    }


}


