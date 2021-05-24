package com.adityaikhbalm.libraries.abstraction.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

fun <T: Any> zipLiveData(vararg liveItems: LiveData<T>): LiveData<List<T>> {
    return MediatorLiveData<List<T>>().apply {
        val zippedObjects = ArrayList<T>()
        liveItems.forEach {
            addSource(it) { item ->
                item?.let { it1 -> zippedObjects.add(it1) }
                if (zippedObjects.size == liveItems.size) {
                    value = zippedObjects
                    zippedObjects.clear()
                }
            }
        }
    }
}
