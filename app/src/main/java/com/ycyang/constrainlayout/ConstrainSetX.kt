package com.ycyang.constrainlayout

import android.os.Bundle
import android.transition.TransitionManager
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.ycyang.drawpractice.R

class ConstrainSetX : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_constraint_set)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint_start)
    }

    fun onClick(view: View) {
        val constraintLayout = view as ConstraintLayout
        val constraintSet = ConstraintSet().apply {
            isForceId = false
            clone(this@ConstrainSetX, R.layout.activity_constraint_end)
        }

        TransitionManager.beginDelayedTransition(constraintLayout)
        constraintSet.applyTo(constraintLayout)
    }

}