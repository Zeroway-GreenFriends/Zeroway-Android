package com.greenfriends.zeroway.ui.communitypostdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.greenfriends.zeroway.databinding.ItemCommunityExcludeImagePostDetailBinding
import com.greenfriends.zeroway.databinding.ItemCommunityIncludeImagePostDetailBinding
import com.greenfriends.zeroway.model.CommunityPostDetailResponse
import com.greenfriends.zeroway.ui.community.CommunityPostAdapter

class CommunityPostDetailAdapter :
    ListAdapter<CommunityPostDetailResponse, RecyclerView.ViewHolder>(
        CommunityPostDetailDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 1) {
            val binding = ItemCommunityIncludeImagePostDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            CommunityPostDetailIncludeImageViewHolder(binding)
        } else {
            val binding = ItemCommunityExcludeImagePostDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            CommunityPostDetailExcludeImageViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == 1) {
            (holder as CommunityPostDetailAdapter.CommunityPostDetailIncludeImageViewHolder).bind(
                getItem(position)
            )
        } else {
            (holder as CommunityPostDetailAdapter.CommunityPostDetailExcludeImageViewHolder).bind(
                getItem(position)
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).imageList.isNotEmpty()) 1 else 0
    }

    class CommunityPostDetailIncludeImageViewHolder(private val binding: ItemCommunityIncludeImagePostDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val communityPostAdapter = CommunityPostAdapter()

        fun bind(communityPostDetailResponse: CommunityPostDetailResponse) {
            binding.itemCommunityIncludeImagePostDetailImageVp.adapter = communityPostAdapter
            communityPostAdapter.submitList(communityPostDetailResponse.imageList)
            binding.itemCommunityIncludeImagePostDetailImageSdi.setViewPager2(binding.itemCommunityIncludeImagePostDetailImageVp)
            binding.communityPostDetail = communityPostDetailResponse
            binding.executePendingBindings()
        }
    }

    class CommunityPostDetailExcludeImageViewHolder(private val binding: ItemCommunityExcludeImagePostDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(communityPostDetailResponse: CommunityPostDetailResponse) {
            binding.communityPostDetail = communityPostDetailResponse
            binding.executePendingBindings()
        }
    }
}

class CommunityPostDetailDiffCallback : DiffUtil.ItemCallback<CommunityPostDetailResponse>() {

    override fun areItemsTheSame(
        oldItem: CommunityPostDetailResponse,
        newItem: CommunityPostDetailResponse
    ): Boolean {
        return oldItem.postId == newItem.postId
    }

    override fun areContentsTheSame(
        oldItem: CommunityPostDetailResponse,
        newItem: CommunityPostDetailResponse
    ): Boolean {
        return oldItem == newItem
    }
}