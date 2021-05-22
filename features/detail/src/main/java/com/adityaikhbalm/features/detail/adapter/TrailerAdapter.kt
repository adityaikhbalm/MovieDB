package com.adityaikhbalm.features.detail.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.adityaikhbalm.core.model.response.Trailer
import com.adityaikhbalm.features.detail.databinding.ItemTrailerBinding
import com.adityaikhbalm.libraries.abstraction.diffutil.TrailerItemDiffUtil
import com.adityaikhbalm.libraries.abstraction.extensions.imageLoader
import com.adityaikhbalm.libraries.utility.Constant

class TrailerAdapter : ListAdapter<Trailer, TrailerAdapter.Holder>(TrailerItemDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemTrailerBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class Holder(
        private val binding: ItemTrailerBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(trailer: Trailer) {
            binding.run {
                trailerCard.setOnClickListener {
                    val appIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("vnd.youtube:" + trailer.key)
                    )

                    val webIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(Constant.url_youtube + trailer.key)
                    )

                    if (appIntent.resolveActivity(root.context.packageManager) != null)
                        root.context.startActivity(appIntent)
                    else root.context.startActivity(webIntent)
                }

                trailerName.text = trailer.name
                trailerImage.imageLoader(
                    shimmer = shimmerTrailer, type = 1, url = trailer.key, youtube = true
                )
            }
        }
    }
}
