package com.adityaikhbalm.features.detail.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.adityaikhbalm.core.model.response.Movie
import com.adityaikhbalm.features.detail.adapter.CastAdapter
import com.adityaikhbalm.features.detail.adapter.SimilarAdapter
import com.adityaikhbalm.features.detail.adapter.TrailerAdapter
import com.adityaikhbalm.features.detail.databinding.ActivityDetailBinding
import com.adityaikhbalm.features.detail.viewmodel.DetailViewModel
import com.adityaikhbalm.libraries.abstraction.extensions.startActivity
import com.adityaikhbalm.libraries.abstraction.extensions.statusLoader
import com.adityaikhbalm.libraries.abstraction.extensions.viewBinding
import com.adityaikhbalm.libraries.abstraction.interactor.ResultState
import org.koin.androidx.viewmodel.ext.android.stateViewModel

class DetailActivity : AppCompatActivity() {

    companion object {
        const val MOVIE_ID = "movie_id"
    }

    private val movieId by lazy { intent.getIntExtra(MOVIE_ID, 0) }
    private val binding by viewBinding(ActivityDetailBinding::inflate)
    private val viewModel: DetailViewModel by stateViewModel()
    private var data: Movie? = null
    private var isFavorite = 0
    private var initialLoad = true

    private val detailAdapter = arrayOf(
        CastAdapter(), TrailerAdapter(),
        SimilarAdapter {
            val bundle = Pair(MOVIE_ID, it.id)
            startActivity<DetailActivity>(bundle)
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState == null) viewModel.getFavorite(movieId)

        setButton()
        setAdapter()
        setViewModel()
    }

    private fun setButton() {
        binding.btnBack.setOnClickListener { onBackPressed() }
        binding.titleLayout.btnFavorite.setOnClickListener {
            data?.let {
                if (isFavorite == 0) viewModel.insertFavorite(it)
                else viewModel.deleteFavorite(it)
            }
        }
    }

    private fun setAdapter() {
        binding.run {
            castLayout.detailRecyclerCast.run {
                setHasFixedSize(false)
                adapter = detailAdapter[0]
            }
            trailerLayout.detailRecyclerTrailer.run {
                setHasFixedSize(false)
                adapter = detailAdapter[1]
            }
            similarLayout.detailRecyclerSimilar.run {
                setHasFixedSize(false)
                adapter = detailAdapter[2]
            }
        }
    }

    private fun setViewModel() {
        viewModel.detail.observe(this) {
            isFavorite = it.data?.favorite ?: 0

            val view = arrayOf(binding.detailNestedScrollView as View)
            val shimmer = arrayOf(binding.shimmerPoster)
            if (initialLoad) {
                it.statusLoader(binding = binding.detailLoader, view = view, shimmer = shimmer) {
                    viewModel.detailMovie(movieId)
                }
            }

            if (it is ResultState.Success) {
                binding.run {
                    setButtonFavorite(this@DetailActivity, it.data?.favorite ?: 0)
                    if (initialLoad) {
                        data = it.data
                        setHeader(this@DetailActivity, it.data)
                        setCategory(detailAdapter, it.data)
                    }
                    initialLoad = false
                }
            }
        }
    }
}
