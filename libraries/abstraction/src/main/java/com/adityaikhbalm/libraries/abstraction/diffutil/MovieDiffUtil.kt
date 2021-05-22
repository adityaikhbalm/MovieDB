package com.adityaikhbalm.libraries.abstraction.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.adityaikhbalm.core.model.response.Cast
import com.adityaikhbalm.core.model.response.Movie
import com.adityaikhbalm.core.model.response.Similar
import com.adityaikhbalm.core.model.response.Trailer

class MovieItemDiffUtil : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(
        oldItem: Movie,
        newItem: Movie
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Movie,
        newItem: Movie
    ): Boolean = oldItem == newItem
}

class CastItemDiffUtil : DiffUtil.ItemCallback<Cast>() {
    override fun areItemsTheSame(
        oldItem: Cast,
        newItem: Cast
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Cast,
        newItem: Cast
    ): Boolean = oldItem == newItem
}

class TrailerItemDiffUtil : DiffUtil.ItemCallback<Trailer>() {
    override fun areItemsTheSame(
        oldItem: Trailer,
        newItem: Trailer
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Trailer,
        newItem: Trailer
    ): Boolean = oldItem == newItem
}

class SimilarItemDiffUtil : DiffUtil.ItemCallback<Similar>() {
    override fun areItemsTheSame(
        oldItem: Similar,
        newItem: Similar
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Similar,
        newItem: Similar
    ): Boolean = oldItem == newItem
}
