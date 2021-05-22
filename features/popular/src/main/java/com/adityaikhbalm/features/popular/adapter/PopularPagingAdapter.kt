package com.adityaikhbalm.features.popular.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.adityaikhbalm.core.model.response.Movie
import com.adityaikhbalm.features.popular.databinding.ItemPopularBinding
import com.adityaikhbalm.libraries.abstraction.diffutil.MovieItemDiffUtil
import com.adityaikhbalm.libraries.abstraction.extensions.imageLoader
import com.adityaikhbalm.libraries.utility.Utility.toastShow

class PopularPagingAdapter(
    private val onClick: (Movie?) -> Unit
) : PagingDataAdapter<Movie, RecyclerView.ViewHolder>(MovieItemDiffUtil()) {

    override fun getItemViewType(position: Int): Int {
        return try {
            if (getItem(position)?.id == 0) TYPE_ERROR else TYPE_DEFAULT
        } catch (e: Exception) {
            TYPE_ERROR
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as Holder).bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemPopularBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return Holder(binding)
    }

    inner class Holder(
        private val binding: ItemPopularBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie?) {
            binding.run {
                popularBackground.setOnClickListener {
                    onClick(movie)
                }
                popularBackground.setOnLongClickListener {
                    root.context.toastShow(movie?.title)
                    return@setOnLongClickListener false
                }

                binding.popularTitle.text = movie?.title
                binding.popularImage.imageLoader(
                    shimmer = shimmerPopular, type = 2, url = movie?.posterPath
                )
            }
        }
    }

    companion object {
        const val TYPE_DEFAULT = 0
        const val TYPE_ERROR = 1
    }
}
