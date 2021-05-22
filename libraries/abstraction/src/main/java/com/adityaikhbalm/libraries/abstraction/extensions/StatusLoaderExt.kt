package com.adityaikhbalm.libraries.abstraction.extensions

import android.view.View
import androidx.core.view.isVisible
import com.adityaikhbalm.libraries.abstraction.interactor.ResultState
import com.adityaikhbalm.libraries.ui.databinding.InitialLoaderBinding
import com.facebook.shimmer.ShimmerFrameLayout

inline fun <T : Any> ResultState<T>.statusLoader(
    binding: InitialLoaderBinding,
    view: Array<View> = arrayOf(),
    shimmer: Array<ShimmerFrameLayout> = arrayOf(),
    crossinline retry: () -> Unit
) {
    binding.run {
        progressCircular.isVisible = this@statusLoader is ResultState.Loading
        btnRetry.isVisible = this@statusLoader is ResultState.Error

        shimmer.forEach {
            if (this@statusLoader is ResultState.Error) it.hideShimmer()
        }

        view.forEach {
            it.isVisible = this@statusLoader is ResultState.Success
        }

        btnRetry.setOnClickListener {
            retry.invoke()
        }
    }
}
