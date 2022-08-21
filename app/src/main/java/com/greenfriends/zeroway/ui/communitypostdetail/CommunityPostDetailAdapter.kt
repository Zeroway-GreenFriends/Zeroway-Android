package com.greenfriends.zeroway.ui.communitypostdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.greenfriends.zeroway.R
import com.greenfriends.zeroway.databinding.ItemCommunityExcludeImagePostDetailBinding
import com.greenfriends.zeroway.databinding.ItemCommunityIncludeImagePostDetailBinding
import com.greenfriends.zeroway.model.CommunityPostDetailResponse
import com.greenfriends.zeroway.ui.community.CommunityPostAdapter

class CommunityPostDetailAdapter :
    ListAdapter<CommunityPostDetailResponse, RecyclerView.ViewHolder>(
        CommunityPostDetailDiffCallback()
    ) {

    private lateinit var onCommunityPostDetailItemClickListener: OnCommunityPostDetailItemClickListener

    fun setOnCommunityPostDetailItemClickListener(onCommunityPostDetailItemClickListener: OnCommunityPostDetailItemClickListener) {
        this.onCommunityPostDetailItemClickListener = onCommunityPostDetailItemClickListener
    }

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
            (holder as CommunityPostDetailIncludeImageViewHolder).bind(
                getItem(position)
            )
            with(holder.binding) {
                itemCommunityIncludeImagePostDetailLikeIv.setOnClickListener {
                    getItem(position).liked = !getItem(position).liked
                    if (getItem(position).liked) {
                        itemCommunityIncludeImagePostDetailLikeIv.setImageResource(R.drawable.ic_like_on)
                        ((itemCommunityIncludeImagePostDetailLikeCountTv.text.substring(0 until itemCommunityIncludeImagePostDetailLikeCountTv.text.length - 1)
                            .toInt() + 1).toString() + "개").also {
                            itemCommunityIncludeImagePostDetailLikeCountTv.text = it
                        }
                    } else {
                        itemCommunityIncludeImagePostDetailLikeIv.setImageResource(R.drawable.ic_like_off)
                        ((itemCommunityIncludeImagePostDetailLikeCountTv.text.substring(0 until itemCommunityIncludeImagePostDetailLikeCountTv.text.length - 1)
                            .toInt() - 1).toString() + "개").also {
                            itemCommunityIncludeImagePostDetailLikeCountTv.text = it
                        }
                    }
                    onCommunityPostDetailItemClickListener.setCommunityPostLike(getItem(position))
                }
            }
        } else {
            (holder as CommunityPostDetailExcludeImageViewHolder).bind(
                getItem(position)
            )
            with(holder.binding) {
                itemCommunityExcludeImagePostDetailLikeIv.setOnClickListener {
                    getItem(position).liked = !getItem(position).liked
                    if (getItem(position).liked) {
                        itemCommunityExcludeImagePostDetailLikeIv.setImageResource(R.drawable.ic_like_on)
                        ((itemCommunityExcludeImagePostDetailLikeCountTv.text.substring(0 until itemCommunityExcludeImagePostDetailLikeCountTv.text.length - 1)
                            .toInt() + 1).toString() + "개").also {
                            itemCommunityExcludeImagePostDetailLikeCountTv.text = it
                        }
                    } else {
                        itemCommunityExcludeImagePostDetailLikeIv.setImageResource(R.drawable.ic_like_off)
                        ((itemCommunityExcludeImagePostDetailLikeCountTv.text.substring(0 until itemCommunityExcludeImagePostDetailLikeCountTv.text.length - 1)
                            .toInt() - 1).toString() + "개").also {
                            itemCommunityExcludeImagePostDetailLikeCountTv.text = it
                        }
                    }
                    onCommunityPostDetailItemClickListener.setCommunityPostLike(getItem(position))
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).imageList.isNotEmpty()) 1 else 0
    }

    class CommunityPostDetailIncludeImageViewHolder(val binding: ItemCommunityIncludeImagePostDetailBinding) :
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

    class CommunityPostDetailExcludeImageViewHolder(val binding: ItemCommunityExcludeImagePostDetailBinding) :
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