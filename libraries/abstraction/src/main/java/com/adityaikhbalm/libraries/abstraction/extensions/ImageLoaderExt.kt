package com.adityaikhbalm.libraries.abstraction.extensions

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.adityaikhbalm.libraries.utility.Constant
import com.adityaikhbalm.libraries.utility.Utility.hide
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.facebook.shimmer.ShimmerFrameLayout

@SuppressLint("CheckResult")
fun ImageView.imageLoader(
    view: Array<View> = arrayOf(),
    shimmer: ShimmerFrameLayout? = null,
    type: Int,
    url: String?,
    youtube: Boolean = false
) {
    val imageSize: String = when (type) {
        0 -> Constant.image_size_500
        1 -> Constant.image_size_185
        else -> Constant.image_size_300
    }

    val fUrl = if (youtube) Constant.image_url_youtube + url + Constant.image_url_youtube_end
    else Constant.image_url + imageSize + url

    Glide.with(this)
        .load(fUrl)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                view.forEach {
                    it.hide()
                }
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                shimmer?.hideShimmer()
                return false
            }
        })
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}
