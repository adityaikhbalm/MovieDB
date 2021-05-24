package com.adityaikhbalm.libraries.abstraction.recyclerview

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView

abstract class LifecycleRecyclerAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), LifecycleOwner {

    private val lifecycleRegistry by lazy { LifecycleRegistry(this) }

    init {
        lifecycleRegistry.currentState = Lifecycle.State.INITIALIZED
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        lifecycleRegistry.currentState = Lifecycle.State.CREATED
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        super.onViewAttachedToWindow(holder)
        lifecycleRegistry.currentState = Lifecycle.State.STARTED
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        lifecycleRegistry.currentState = Lifecycle.State.RESUMED
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        lifecycleRegistry.currentState = Lifecycle.State.DESTROYED
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }
}