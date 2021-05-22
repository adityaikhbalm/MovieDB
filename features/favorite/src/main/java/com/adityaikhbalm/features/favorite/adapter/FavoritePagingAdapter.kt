package com.adityaikhbalm.features.favorite.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.adityaikhbalm.core.model.response.Movie
import com.adityaikhbalm.features.favorite.databinding.ItemFavoriteBinding
import com.adityaikhbalm.libraries.abstraction.diffutil.MovieItemDiffUtil
import com.adityaikhbalm.libraries.abstraction.extensions.imageLoader
import com.adityaikhbalm.libraries.utility.Utility.hide
import com.adityaikhbalm.libraries.utility.Utility.toastShow
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class FavoritePagingAdapter(
    private val detailClick: (Movie?) -> Unit,
    private val removeClick: (Movie?) -> Unit
) : PagingDataAdapter<Movie, RecyclerView.ViewHolder>(MovieItemDiffUtil()) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as Holder).bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemFavoriteBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return Holder(binding)
    }

    inner class Holder(
        private val binding: ItemFavoriteBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SimpleDateFormat")
        fun bind(movie: Movie?) {
            binding.run {
                btnFavorite.setOnClickListener {
                    AlertDialog.Builder(binding.root.context)
                        .setTitle("Are you sure?")
                        .setMessage("Do you really want to delete these records? This process cannot be undone.")
                        .setPositiveButton("Yes") { dialog, _ ->
                            removeClick.invoke(movie)
                            dialog.dismiss()
                        }.setNegativeButton("No") { dialog, _ ->
                            dialog.dismiss()
                        }.create().show()
                }

                favoriteBackground.setOnClickListener {
                    detailClick(movie)
                }

                favoriteBackground.setOnLongClickListener {
                    binding.root.context.toastShow(movie?.title)
                    return@setOnLongClickListener false
                }

                favoriteTitle.text = movie?.title
                val year = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    LocalDate.parse(movie?.releaseDate).year.toString()
                } else {
                    movie?.releaseDate?.let {
                        val date = SimpleDateFormat("yyyy").parse(it)
                        if (date != null) SimpleDateFormat("yyyy").format(date).toString()
                        else null
                    }
                }

                if (year.isNullOrEmpty()) favoriteYear.hide()
                else favoriteYear.text = year

                val genre = movie?.genres?.map {
                    it.name
                }
                val genreString = genre?.filterIndexed { index, _ ->
                    index < 3
                }?.joinToString()
                favoriteGenre.text = genreString
                popularImage.imageLoader(
                    shimmer = binding.shimmerFavorite, type = 1, url = movie?.backdropPath
                )
            }
        }
    }
}
