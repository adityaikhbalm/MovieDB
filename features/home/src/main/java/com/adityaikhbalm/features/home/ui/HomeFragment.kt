package com.adityaikhbalm.features.home.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.fragment.app.Fragment
import com.adityaikhbalm.features.home.R
import com.adityaikhbalm.features.home.adapter.HomeAdapter
import com.adityaikhbalm.features.home.databinding.FragmentHomeBinding
import com.adityaikhbalm.features.home.viewmodel.HomeViewModel
import com.adityaikhbalm.libraries.abstraction.extensions.viewBinding
import org.koin.androidx.viewmodel.ext.android.stateViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by stateViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        if (savedInstanceState == null) viewModel.initialLoad()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
    }

    private fun setAdapter() {
        binding.homeRecyclerView.run {
            setHasFixedSize(false)
            adapter = HomeAdapter(viewModel)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.findItem(R.id.search_menu).isVisible = false
        super.onCreateOptionsMenu(menu, inflater)
    }
}
