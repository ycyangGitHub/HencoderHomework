package com.ycyang.drawpractice

import android.util.Log

class NewActivity {
    val array: Array<Int> = Array(100_000, init = { i -> i + 1 })
    val intArray: IntArray = IntArray(100_000, init = { i -> i + 1 })
    val list: List<Int> = List(100_000, init = { i -> i + 1 })

    companion object {
        var INSTANCE: NewActivity = NewActivity()
    }

    init {
        printRunTime()
    }

    fun printRunTime() {
        var now = System.nanoTime()
        var sum: Int = 0
        for (i in array) {
            sum += i
        }
        sum / array.size.toFloat()
        Log.e("time", "array:" + (System.nanoTime() - now))
        now = System.nanoTime()
        for (i in intArray) {
            sum += i
        }
        sum / intArray.size.toFloat()
        Log.e("time", "intArray:" + (System.nanoTime() - now))
        now = System.nanoTime()
        for (i in list) {
            sum += i
        }
        sum / list.size.toFloat()
        Log.e("time", "list:" + (System.nanoTime() - now))
        intArray.filter { i -> i % 8 == 0 }

    }
}

class Test {
    var n: NewActivity = NewActivity.INSTANCE
}

class Student constructor(var name: String, var gender: Int) {
    init {
        show()
    }

    constructor(name: String, gender: Int, age: Int) : this(name, gender) {

    }

    fun show() {
        print("$name+$gender")
        val list: List<Int> = arrayListOf(21, 40, 11, 33, 78)
        list.filter { it % 3 == 0 }.forEach { Log.e("ycyang","可以被三整除的数$it") }
    }

}


