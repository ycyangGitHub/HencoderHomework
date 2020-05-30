package com.ycyang.drag

import android.content.Context
import android.graphics.Canvas
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.util.AttributeSet
import android.view.DragEvent
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.drag_listener_practice.view.*

class DragListenerPractice(context: Context, attrs: AttributeSet?) : RelativeLayout(context, attrs) {
    private var dragListener: OnDragListener = MyDragListener()
    private var dragableView: View? = null
    override fun onFinishInflate() {
        super.onFinishInflate()
        vactor.setOnLongClickListener { v ->
            dragableView = v
            v.startDrag(null, DragShadowBuilder(v), null, 0)
        }
        dragHere.setOnDragListener(dragListener)
    }


    inner class MyDragListener : OnDragListener {
        override fun onDrag(v: View?, event: DragEvent): Boolean {
            when (event.action) {

                DragEvent.ACTION_DROP -> if (v is DragEnterView) {
                    Toast.makeText(context, "Droped Here", Toast.LENGTH_LONG).show()
                }
            }

            return true
        }

    }

}

class DragEnterView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private var paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 10.toFloat()
        strokeJoin = Paint.Join.ROUND
        pathEffect = DashPathEffect(floatArrayOf(10.0f, 10.0f), 10.toFloat())
    }
    private var paintNoEffect = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 10.toFloat()
        strokeCap = Paint.Cap.SQUARE

    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawLine(width / 2 - 50.toFloat(), height / 2.toFloat(), width / 2 + 50.toFloat(), height / 2.toFloat(), paintNoEffect)
        canvas.drawLine(width / 2.toFloat(), height / 2 - 50.toFloat(), width / 2.toFloat(), height / 2 + 50.toFloat(), paintNoEffect)
        canvas.drawRoundRect(width / 2 - 150.toFloat(), height / 2 - 150.toFloat(), width / 2 + 150.toFloat(), height / 2 + 150.toFloat(), 20.toFloat(), 20.toFloat(), paint)

    }
}