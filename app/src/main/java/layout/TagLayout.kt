package layout

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.core.view.children
import java.lang.Integer.max

class TagLayout(context: Context, attrs: AttributeSet?) : ViewGroup(context, attrs) {
    var childrenBounds = mutableListOf<Rect>()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var widthUsed = 0
        var heightUsed = 0
        var lineWidthUsed = 0
        var lineMaxHeightUsed = 0
        val specWidth = MeasureSpec.getSize(widthMeasureSpec)
        val specHeight = MeasureSpec.getSize(heightMeasureSpec)

        for ((index, child) in children.withIndex()) {

            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed)
            if (index <= childrenBounds.size) {
                childrenBounds.add(index, Rect())
            }
            if (widthUsed + child.measuredWidth > specWidth) {
                widthUsed = 0
                heightUsed +=lineMaxHeightUsed
            }
            childrenBounds[index].left = widthUsed
            childrenBounds[index].top = heightUsed
            childrenBounds[index].right = widthUsed + child.measuredWidth
            childrenBounds[index].bottom = heightUsed + child.measuredHeight
            widthUsed += child.measuredWidth
            lineMaxHeightUsed = max(lineMaxHeightUsed, child.measuredHeight)
        }
        setMeasuredDimension(specWidth, specHeight)

    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for ((index, child) in children.withIndex()) {
            if (index < childrenBounds.size) {
                val childBounds = childrenBounds[index]
                child.layout(childBounds.left, childBounds.top, childBounds.right, childBounds.bottom)
            }
        }
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }
}