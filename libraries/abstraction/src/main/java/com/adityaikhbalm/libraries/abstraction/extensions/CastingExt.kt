package com.adityaikhbalm.libraries.abstraction.extensions

inline fun <reified T: Any> Any.cast(): T{
    return this as T
}