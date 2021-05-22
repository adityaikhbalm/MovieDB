package com.adityaikhbalm.features.favorite.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.adityaikhbalm.features.detail.ui.DetailActivity
import com.adityaikhbalm.features.favorite.R
import com.adityaikhbalm.features.favorite.adapter.FavoritePagingAdapter
import com.adityaikhbalm.features.favorite.databinding.FragmentFavoriteBinding
import com.adityaikhbalm.features.favorite.viewmodel.FavoriteViewModel
import com.adityaikhbalm.libraries.abstraction.extensions.BaseSearch
import com.adityaikhbalm.libraries.abstraction.extensions.setMenu
import com.adityaikhbalm.libraries.abstraction.extensions.startActivity
import com.adityaikhbalm.libraries.abstraction.extensions.viewBinding
import com.adityaikhbalm.libraries.utility.Utility.hide
import com.adityaikhbalm.libraries.utility.Utility.show
import org.koin.androidx.viewmodel.ext.android.stateViewModel

class FavoriteFragment : Fragment(R.layout.fragment_favorite), BaseSearch {

    private val binding by viewBinding(FragmentFavoriteBinding::bind)
    private val viewModel: FavoriteViewModel by stateViewModel()
    override var menuFocus = false
    override var searchText: String? = ""
    override var searchSubmit = false

    private val favoritePagingAdapter by lazy {
        FavoritePagingAdapter(
            {
                val bundle = Pair(DetailActivity.MOVIE_ID, it?.id)
                activity?.startActivity<DetailActivity>(bundle)
            },
            {
                if (it != null) viewModel.deleteFavorite(it)
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        if (savedInstanceState == null) viewModel.getAllFavorite()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
        setViewModel()
    }

    private fun setAdapter() {
        favoritePagingAdapter.addLoadStateListener {
            if (it.source.refresh is LoadState.NotLoading &&
                it.append.endOfPaginationReached && favoritePagingAdapter.itemCount < 1
            ) {
                binding.noFavorite.show()
            } else binding.noFavorite.hide()
        }

        binding.favoriteRecyclerView.run {
            setHasFixedSize(false)
            adapter = favoritePagingAdapter
        }
    }

    private fun setViewModel() {
        lifecycleScope.launchWhenResumed {
            viewModel.favorite.observe(viewLifecycleOwner) {
                favoritePagingAdapter.submitData(lifecycle, it)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("searchText", searchText)
        outState.putBoolean("menuFocus", menuFocus)
        outState.putBoolean("searchSubmit", searchSubmit)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        searchText = savedInstanceState?.getString("searchText")
        menuFocus = savedInstanceState?.getBoolean("menuFocus") ?: false
        searchSubmit = savedInstanceState?.getBoolean("searchSubmit") ?: false
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        val searchMenuItem = menu.findItem(R.id.search_menu)
        this.setMenu(
            requireContext(),
            searchMenuItem,
            { viewModel.getAllFavorite() },
            { if (searchText != null) viewModel.searchFavorite(searchText!!) }
        )
    }
}
