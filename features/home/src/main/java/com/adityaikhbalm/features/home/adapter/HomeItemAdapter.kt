package com.adityaikhbalm.features.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.adityaikhbalm.core.model.response.Movie
import com.adityaikhbalm.features.home.databinding.ItemHomeBinding
import com.adityaikhbalm.libraries.abstraction.diffutil.MovieItemDiffUtil
import com.adityaikhbalm.libraries.abstraction.extensions.imageLoader
import com.adityaikhbalm.libraries.utility.Utility.convertDpToPixel
import com.adityaikhbalm.libraries.utility.Utility.toastShow

class HomeItemAdapter(
    private val type: Int,
    private val onClick: (Movie) -> Unit
) : ListAdapter<Movie, HomeItemAdapter.Holder>(MovieItemDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemHomeBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        if (type == 0) {
            binding.homeBackground.radius = 0f
        } else {
            val lp = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
            lp.width = 120.convertDpToPixel
            lp.height = 180.convertDpToPixel
            lp.setMargins(15.convertDpToPixel, 0, 0, 0)
            binding.root.layoutParams = lp
        }

        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position), type)
    }

    inner class Holder(
        private val binding: ItemHomeBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie, type: Int) {
            binding.run {
                homeBackground.setOnClickListener {
                    onClick(movie)
                }
                homeBackground.setOnLongClickListener {
                    root.context.toastShow(movie.title)
                    return@setOnLongClickListener false
                }

                val url = if (type == 0) movie.backdropPath else movie.posterPath
                homeImage.imageLoader(shimmer = shimmerHome, type = type, url = url)
            }
        }
    }
}
