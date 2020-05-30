package com.ycyang.drag

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.ViewCompat
import androidx.customview.widget.ViewDragHelper

class DragViewHelperPractice(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    private var dragHelper = ViewDragHelper.create(this, DragCallback())

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return dragHelper.shouldInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        dragHelper.processTouchEvent(event)
        return true
    }

    override fun computeScroll() {
        if (dragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this)
        }
    }

    inner class DragCallback : ViewDragHelper.Callback() {
        var originTop = 0
        var releasLeft = 0
        var curScrollX = 0
        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            return true
        }

        override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
            return left
        }

        override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
            return originTop
        }

        override fun onViewCaptured(capturedChild: View, activePointerId: Int) {
            originTop = capturedChild.top
        }

        override fun onViewPositionChanged(changedView: View, left: Int, top: Int, dx: Int, dy: Int) {
            curScrollX = left
        }

        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            releasLeft = if (curScrollX > width / 2) width - releasedChild.width else 0
            dragHelper.settleCapturedViewAt(releasLeft, originTop)
            postInvalidateOnAnimation()


        }


    }

}