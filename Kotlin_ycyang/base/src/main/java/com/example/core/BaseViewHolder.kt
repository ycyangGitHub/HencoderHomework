package com.example.core

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView

open abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    @SuppressLint("UseSparseArrays")
    private val viewHashMap: MutableMap<Int, View> = HashMap()

    protected fun <T : View?> getView(@IdRes id: Int): T? {
        viewHashMap[id] = viewHashMap[id] ?: itemView.findViewById(id)
        return viewHashMap[id] as T?
    }

    protected fun setText(@IdRes id: Int, text: String?) {
        getView<TextView>(id)?.text = text
    }

}