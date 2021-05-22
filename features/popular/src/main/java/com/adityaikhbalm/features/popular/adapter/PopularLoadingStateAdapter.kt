package com.adityaikhbalm.features.popular.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.adityaikhbalm.libraries.ui.databinding.InitialLoaderBinding

class PopularLoadingStateAdapter(
    private val adapter: PopularPagingAdapter
) : LoadStateAdapter<PopularLoadingStateAdapter.NetworkStateItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) =
        NetworkStateItemViewHolder(
            InitialLoaderBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        ) { adapter.retry() }

    override fun onBindViewHolder(holder: NetworkStateItemViewHolder, loadState: LoadState) =
        holder.bind(loadState)

    inner class NetworkStateItemViewHolder(
        private val binding: InitialLoaderBinding,
        private val retryCallback: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnRetry.setOnClickListener { retryCallback() }
        }

        fun bind(loadState: LoadState) {
            with(binding) {
                progressCircular.isVisible = loadState is LoadState.Loading
                btnRetry.isVisible = loadState is LoadState.Error
            }
        }
    }
}
