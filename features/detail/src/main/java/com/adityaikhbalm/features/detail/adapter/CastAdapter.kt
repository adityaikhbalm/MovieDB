package com.adityaikhbalm.features.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.adityaikhbalm.core.model.response.Cast
import com.adityaikhbalm.features.detail.databinding.ItemCastBinding
import com.adityaikhbalm.libraries.abstraction.diffutil.CastItemDiffUtil
import com.adityaikhbalm.libraries.abstraction.extensions.imageLoader

class CastAdapter : ListAdapter<Cast, CastAdapter.Holder>(CastItemDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemCastBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class Holder(
        private val binding: ItemCastBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cast: Cast) {
            binding.run {
                castName.text = cast.name
                castImage.imageLoader(shimmer = shimmerCast, type = 1, url = cast.profilePath)
            }
        }
    }
}
