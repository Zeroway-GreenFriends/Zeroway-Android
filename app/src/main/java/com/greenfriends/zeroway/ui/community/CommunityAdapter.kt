package com.greenfriends.zeroway.ui.community

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.greenfriends.zeroway.databinding.ItemCommunityExcludeImagePostBinding
import com.greenfriends.zeroway.databinding.ItemCommunityIncludeImagePostBinding
import com.greenfriends.zeroway.model.CommunityPost

class CommunityAdapter :
    ListAdapter<CommunityPost, RecyclerView.ViewHolder>(CommunityDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 1) {
            val binding = ItemCommunityIncludeImagePostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            CommunityIncludeImageViewHolder(binding)
        } else {
            val binding = ItemCommunityExcludeImagePostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            CommunityExcludeImageViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == 1) {
            (holder as CommunityIncludeImageViewHolder).bind(getItem(position))
        } else {
            (holder as CommunityExcludeImageViewHolder).bind(getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).imageList.isNotEmpty()) 1 else 0
    }

    inner class CommunityIncludeImageViewHolder(private val binding: ItemCommunityIncludeImagePostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(communityPost: CommunityPost) {
            val communityPostAdapter = CommunityPostAdapter()
            binding.itemCommunityIncludeImageImageVp.adapter = communityPostAdapter
            communityPostAdapter.submitList(communityPost.imageList)
            binding.itemCommunityIncludeImageImageSdi.setViewPager2(binding.itemCommunityIncludeImageImageVp)
            binding.communityPost = communityPost
            binding.executePendingBindings()
        }
    }

    inner class CommunityExcludeImageViewHolder(private val binding: ItemCommunityExcludeImagePostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(communityPost: CommunityPost) {
            binding.communityPost = communityPost
            binding.executePendingBindings()
        }
    }
}

class CommunityDiffCallback : DiffUtil.ItemCallback<CommunityPost>() {

    override fun areItemsTheSame(oldItem: CommunityPost, newItem: CommunityPost): Boolean {
        return oldItem.postId == newItem.postId
    }

    override fun areContentsTheSame(oldItem: CommunityPost, newItem: CommunityPost): Boolean {
        return oldItem == newItem
    }
}