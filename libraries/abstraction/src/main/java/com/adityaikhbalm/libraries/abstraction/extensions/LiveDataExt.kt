package com.adityaikhbalm.libraries.abstraction.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

fun <T1, T2, T3> combineLiveData(
    f1: LiveData<T1>,
    f2: LiveData<T2>,
    f3: LiveData<T3>
): LiveData<Triple<T1?, T2?, T3?>> = MediatorLiveData<Triple<T1?, T2?, T3?>>().also { mediator ->
    mediator.value = Triple(f1.value, f2.value, f3.value)

    mediator.addSource(f1) { t1: T1? ->
        val (_, t2, t3) = mediator.value!!
        mediator.value = Triple(t1, t2, t3)
    }

    mediator.addSource(f2) { t2: T2? ->
        val (t1, _, t3) = mediator.value!!
        mediator.value = Triple(t1, t2, t3)
    }

    mediator.addSource(f3) { t3: T3? ->
        val (t1, t2, _) = mediator.value!!
        mediator.value = Triple(t1, t2, t3)
    }
}
