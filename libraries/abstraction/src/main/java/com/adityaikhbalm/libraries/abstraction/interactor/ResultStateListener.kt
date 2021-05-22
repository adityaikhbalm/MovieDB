package com.adityaikhbalm.libraries.abstraction.interactor

interface ResultStateListener<T : Any> {
    fun onLoading()
    fun onSuccess(data: T)
    fun onError(throwable: Throwable)
}
