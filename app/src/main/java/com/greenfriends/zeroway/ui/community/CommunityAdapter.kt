package com.greenfriends.zeroway.ui.community

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.greenfriends.zeroway.R
import com.greenfriends.zeroway.databinding.ItemCommunityExcludeImagePostBinding
import com.greenfriends.zeroway.databinding.ItemCommunityIncludeImagePostBinding
import com.greenfriends.zeroway.model.CommunityPost

class CommunityAdapter(
    private val viewModel: CommunityViewModel,
) :
    ListAdapter<CommunityPost, RecyclerView.ViewHolder>(CommunityDiffCallback()) {

    private lateinit var onCommunityItemClickListener: OnCommunityItemClickListener

    fun setOnCommunityItemClickListener(onCommunityItemClickListener: OnCommunityItemClickListener) {
        this.onCommunityItemClickListener = onCommunityItemClickListener
    }

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
            with(holder.binding) {
                itemCommunityIncludeImageLikeIv.setOnClickListener {
                    getItem(position).liked = !getItem(position).liked
                    if (getItem(position).liked) {
                        itemCommunityIncludeImageLikeIv.setImageResource(R.drawable.ic_like_on)
                        ((itemCommunityIncludeImageLikeCountTv.text.substring(0 until itemCommunityIncludeImageLikeCountTv.text.length - 1)
                            .toInt() + 1).toString() + "개").also {
                            itemCommunityIncludeImageLikeCountTv.text = it
                        }
                    } else {
                        itemCommunityIncludeImageLikeIv.setImageResource(R.drawable.ic_like_off)
                        ((itemCommunityIncludeImageLikeCountTv.text.substring(0 until itemCommunityIncludeImageLikeCountTv.text.length - 1)
                            .toInt() - 1).toString() + "개").also {
                            itemCommunityIncludeImageLikeCountTv.text = it
                        }
                    }
                    onCommunityItemClickListener.setCommunityPostLike(getItem(position))
                }

                itemCommunityIncludeImageBookmarkIv.setOnClickListener {
                    getItem(position).bookmarked = !getItem(position).bookmarked
                    if (getItem(position).bookmarked) {
                        itemCommunityIncludeImageBookmarkIv.setImageResource(R.drawable.ic_bookmark_on)
                    } else {
                        itemCommunityIncludeImageBookmarkIv.setImageResource(R.drawable.ic_bookmark_off)
                    }
                    onCommunityItemClickListener.setCommunityPostBookmark(getItem(position))
                }
            }
        } else {
            (holder as CommunityExcludeImageViewHolder).bind(getItem(position))
            with(holder.binding) {
                itemCommunityExcludeImageLikeIv.setOnClickListener {
                    getItem(position).liked = !getItem(position).liked
                    if (getItem(position).liked) {
                        itemCommunityExcludeImageLikeIv.setImageResource(R.drawable.ic_like_on)
                        ((itemCommunityExcludeImageLikeCountTv.text.substring(0 until itemCommunityExcludeImageLikeCountTv.text.length - 1)
                            .toInt() + 1).toString() + "개").also {
                            itemCommunityExcludeImageLikeCountTv.text = it
                        }
                    } else {
                        itemCommunityExcludeImageLikeIv.setImageResource(R.drawable.ic_like_off)
                        ((itemCommunityExcludeImageLikeCountTv.text.substring(0 until itemCommunityExcludeImageLikeCountTv.text.length - 1)
                            .toInt() - 1).toString() + "개").also {
                            itemCommunityExcludeImageLikeCountTv.text = it
                        }
                    }
                    onCommunityItemClickListener.setCommunityPostLike(getItem(position))
                }

                itemCommunityExcludeImageBookmarkIv.setOnClickListener {
                    getItem(position).bookmarked = !getItem(position).bookmarked
                    if (getItem(position).bookmarked) {
                        itemCommunityExcludeImageBookmarkIv.setImageResource(R.drawable.ic_bookmark_on)
                    } else {
                        itemCommunityExcludeImageBookmarkIv.setImageResource(R.drawable.ic_bookmark_off)
                    }
                    onCommunityItemClickListener.setCommunityPostBookmark(getItem(position))
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).imageList.isNotEmpty()) 1 else 0
    }

    inner class CommunityIncludeImageViewHolder(val binding: ItemCommunityIncludeImagePostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val communityPostAdapter = CommunityPostAdapter()

        fun bind(communityPost: CommunityPost) {
            binding.itemCommunityIncludeImageImageVp.adapter = communityPostAdapter
            communityPostAdapter.submitList(communityPost.imageList)
            binding.itemCommunityIncludeImageImageSdi.setViewPager2(binding.itemCommunityIncludeImageImageVp)
            binding.viewModel = viewModel
            binding.communityPost = communityPost
            binding.executePendingBindings()
        }
    }

    inner class CommunityExcludeImageViewHolder(val binding: ItemCommunityExcludeImagePostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(communityPost: CommunityPost) {
            binding.viewModel = viewModel
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