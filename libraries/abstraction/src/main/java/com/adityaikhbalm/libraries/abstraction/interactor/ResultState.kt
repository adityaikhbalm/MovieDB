package com.adityaikhbalm.libraries.abstraction.interactor

sealed class ResultState<T : Any>(val data: T? = null, val throwable: Throwable? = null) {
    class Loading<T : Any> : ResultState<T>()
    data class Success<T : Any>(val dt: T) : ResultState<T>(data = dt)
    data class Error<T : Any>(val th: Throwable) : ResultState<T>(throwable = th)
}
