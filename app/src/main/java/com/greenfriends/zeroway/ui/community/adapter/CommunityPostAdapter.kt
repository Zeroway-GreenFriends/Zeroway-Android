package com.greenfriends.zeroway.ui.community.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.greenfriends.zeroway.databinding.ItemCommunityPostImageBinding

class CommunityPostAdapter :
    ListAdapter<String, CommunityPostAdapter.CommunityPostViewHolder>(CommunityPostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityPostViewHolder {
        val binding = ItemCommunityPostImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CommunityPostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommunityPostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CommunityPostViewHolder(private val binding: ItemCommunityPostImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(imageUrl: String) {
            binding.imageUrl = imageUrl
            binding.executePendingBindings()
        }
    }
}

class CommunityPostDiffCallback : DiffUtil.ItemCallback<String>() {

    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}