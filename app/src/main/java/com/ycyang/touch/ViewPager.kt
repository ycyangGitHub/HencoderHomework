package com.ycyang.touch

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.ViewConfiguration
import android.view.ViewGroup
import android.widget.OverScroller
import androidx.core.view.children
import kotlin.math.abs

class ViewPager(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {
    private var downX = 0f
    private var downY = 0f
    private var downScrollX = 0f

    private val viewConfiguration: ViewConfiguration = ViewConfiguration.get(context)
    private var pagingSlop = viewConfiguration.scaledPagingTouchSlop
    private var scrolling = false
    private val overScroller: OverScroller = OverScroller(context)

    private val velocityTracker = VelocityTracker.obtain()
    private var maxVelocity = viewConfiguration.scaledMaximumFlingVelocity
    private var minVelocity = viewConfiguration.scaledMinimumFlingVelocity

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        measureChildren(widthMeasureSpec, heightMeasureSpec)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var left = 0
        var right = width
        var top = 0
        var bottom = height
        for (child in children) {
            child.layout(left, top, right, bottom)
            left += width
            right += width
        }

    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        var result = false
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                scrolling = false
                downX = event.x
                downY = event.y
            }
            MotionEvent.ACTION_MOVE -> if (!scrolling) {
                val dx = event.x - downX
                if (abs(dx) > pagingSlop) {
                    result = true
                    scrolling = true//   child.cancel
                    parent.requestDisallowInterceptTouchEvent(true)

                }
            }

        }

        return result
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.actionMasked == MotionEvent.ACTION_DOWN) {
            velocityTracker.clear()
        }
        velocityTracker.addMovement(event)
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                downX = event.x
                downY = event.y
                downScrollX = scrollX.toFloat()

            }
            MotionEvent.ACTION_MOVE -> {
                val dx = (downX - event.x + downScrollX).toInt().coerceAtLeast(0).coerceAtMost(width)
                scrollTo(dx, 0)

            }
            MotionEvent.ACTION_UP -> {
                velocityTracker.computeCurrentVelocity(1000, maxVelocity.toFloat())
                val vx = velocityTracker.xVelocity
                val targetPage = if (abs(vx) < minVelocity) {
                    if (scrollX > width / 2) 1 else 0
                } else {
                    //右滑 左滑
                    if (vx < 0) 1 else 0
                }
                val scrollDistance = if (targetPage == 1) width - scrollX else -scrollX
                overScroller.startScroll(scrollX, 0, scrollDistance, 0)
                postInvalidateOnAnimation()

            }
        }
        return true
    }

    override fun computeScroll() {
        if (overScroller.computeScrollOffset()) {
            scrollTo(overScroller.currX, overScroller.currY)
            postInvalidateOnAnimation()
        }
    }

}