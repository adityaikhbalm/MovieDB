package com.adityaikhbalm.libraries.abstraction.extensions

import com.adityaikhbalm.libraries.abstraction.interactor.ResultState
import com.adityaikhbalm.libraries.abstraction.interactor.ResultStateListener
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

suspend fun <T : Any> fetch(call: suspend () -> T): Flow<ResultState<T>> = flow {
    emit(ResultState.Loading<T>())
    try {
        emit(ResultState.Success(dt = call.invoke()))
    } catch (e: Throwable) {
        emit(ResultState.Error<T>(th = e))
    }
}

inline fun <reified T : Any> ResultState<T>.listenOn(stateListener: ResultStateListener<T>) = run {
    when (this) {
        is ResultState.Loading -> stateListener.onLoading()
        is ResultState.Success -> stateListener.onSuccess(dt)
        is ResultState.Error -> stateListener.onError(th)
    }
}
