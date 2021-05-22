package com.adityaikhbalm.features.home.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.*
import com.adityaikhbalm.features.detail.ui.DetailActivity
import com.adityaikhbalm.features.home.R
import com.adityaikhbalm.features.home.adapter.HomeAdapter
import com.adityaikhbalm.features.home.databinding.CategoryHomeBinding
import com.adityaikhbalm.features.home.databinding.FragmentHomeBinding
import com.adityaikhbalm.features.home.viewmodel.HomeViewModel
import com.adityaikhbalm.libraries.abstraction.extensions.startActivity
import com.adityaikhbalm.libraries.abstraction.extensions.statusLoader
import com.adityaikhbalm.libraries.abstraction.extensions.viewBinding
import org.koin.androidx.viewmodel.ext.android.stateViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private lateinit var category: CategoryHomeBinding
    private val viewModel: HomeViewModel by stateViewModel()
    private val homeAdapter = arrayOfNulls<HomeAdapter>(3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        if (savedInstanceState == null) viewModel.initialLoad()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCategory()
        setAdapter()
        setViewModel()
    }

    private fun setCategory() {
        category = CategoryHomeBinding.bind(binding.root)
        LinearSnapHelper().attachToRecyclerView(binding.bannerRecyclerView)
    }

    private fun setAdapter() {
        val rc = arrayOf(
            binding.bannerRecyclerView, category.popularRecyclerView, category.comingRecyclerView
        )

        for (i in rc.indices) {
            val n = if (i == 2) 1 else i
            homeAdapter[i] = HomeAdapter(n) {
                val bundle = Pair(DetailActivity.MOVIE_ID, it.id)
                activity?.startActivity<DetailActivity>(bundle)
            }
            rc[i].run {
                setHasFixedSize(false)
                adapter = homeAdapter[i]
            }
        }
    }

    private fun setViewModel() {
        viewModel.banner.observe(viewLifecycleOwner) {
            it.statusLoader(binding = binding.bannerLoader) {
                viewModel.bannerLoad()
            }
            val movie = it.data?.toMutableList()
            movie?.subList(movie.size - 15, movie.size)?.clear()
            binding.bannerIndicator.attachToRecyclerView(binding.bannerRecyclerView)
            homeAdapter[0]?.submitList(movie)
        }

        viewModel.popular.observe(viewLifecycleOwner) {
            it.statusLoader(binding = category.popularLoader) {
                viewModel.popularLoad()
            }
            homeAdapter[1]?.submitList(it.data)
        }

        viewModel.coming.observe(viewLifecycleOwner) {
            it.statusLoader(binding = category.comingLoader) {
                viewModel.comingLoad()
            }
            homeAdapter[2]?.submitList(it.data)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.findItem(R.id.search_menu).isVisible = false
        super.onCreateOptionsMenu(menu, inflater)
    }
}
