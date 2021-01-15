package com.base.core.extensions

import android.annotation.SuppressLint
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.base.core.ui.recyclerview.RecyclerViewAdapter

@SuppressLint("WrongConstant")
fun RecyclerView.setup(
    adapter: RecyclerViewAdapter,
    layoutManager: LinearLayoutManager = LinearLayoutManager(this.context),
    isHasFixedSize: Boolean = false
) {
    this.layoutManager = layoutManager
    this.setHasFixedSize(isHasFixedSize)
    adapter.let {
        this.adapter = adapter
    }
}

@SuppressLint("WrongConstant")
fun RecyclerView.setupStaggered(
    adapter: RecyclerViewAdapter,
    layoutManager: StaggeredGridLayoutManager = StaggeredGridLayoutManager(3,LinearLayoutManager.VERTICAL),
    isHasFixedSize: Boolean = false
) {
    this.layoutManager = layoutManager
    this.setHasFixedSize(isHasFixedSize)
    adapter.let {
        this.adapter = adapter
    }
}