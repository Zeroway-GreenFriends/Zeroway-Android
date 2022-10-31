package com.greenfriends.zeroway.presentation.community.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.greenfriends.zeroway.R
import com.greenfriends.zeroway.data.model.CommunityPostDetailComment
import com.greenfriends.zeroway.databinding.ItemCommunityPostCommentBinding
import com.greenfriends.zeroway.presentation.community.OnCommunityPostDetailCommentClickListener

class CommunityPostDetailCommentAdapter :
    ListAdapter<CommunityPostDetailComment, CommunityPostDetailCommentAdapter.CommunityPostDetailCommentViewHolder>(
        CommunityPostDetailCommentDiffCallback()
    ) {

    private lateinit var onCommunityPostDetailCommentClickListener: OnCommunityPostDetailCommentClickListener

    fun setOnCommunityPostDetailCommentClickListener(onCommunityPostDetailCommentClickListener: OnCommunityPostDetailCommentClickListener) {
        this.onCommunityPostDetailCommentClickListener = onCommunityPostDetailCommentClickListener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommunityPostDetailCommentViewHolder {
        val binding = ItemCommunityPostCommentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CommunityPostDetailCommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommunityPostDetailCommentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CommunityPostDetailCommentViewHolder(private val binding: ItemCommunityPostCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(communityPostDetailComment: CommunityPostDetailComment) {
            binding.communityPostDetailComment = communityPostDetailComment
            binding.executePendingBindings()

            with(binding) {
                itemCommunityPostCommentLikeIv.setOnClickListener {
                    communityPostDetailComment.liked = !communityPostDetailComment.liked
                    if (communityPostDetailComment.liked) {
                        itemCommunityPostCommentLikeIv.setImageResource(R.drawable.ic_like_on)
                        (itemCommunityPostCommentLikeCountTv.text.substring(0)
                            .toInt() + 1).toString()
                            .also { itemCommunityPostCommentLikeCountTv.text = it }
                    } else {
                        itemCommunityPostCommentLikeIv.setImageResource(R.drawable.ic_like_off)
                        (itemCommunityPostCommentLikeCountTv.text.substring(0)
                            .toInt() - 1).toString()
                            .also { itemCommunityPostCommentLikeCountTv.text = it }
                    }
                    onCommunityPostDetailCommentClickListener.setCommunityPostCommentLike(
                        communityPostDetailComment
                    )
                }

                itemCommunityPostCommentDeleteTv.setOnClickListener {
                    onCommunityPostDetailCommentClickListener.deleteCommunityPostComment(
                        communityPostDetailComment
                    )
                }

                itemCommunityPostCommentReportTv.setOnClickListener {
                    onCommunityPostDetailCommentClickListener.reportCommunityPostComment(
                        communityPostDetailComment
                    )
                }
            }
        }
    }
}

class CommunityPostDetailCommentDiffCallback : DiffUtil.ItemCallback<CommunityPostDetailComment>() {

    override fun areItemsTheSame(
        oldItem: CommunityPostDetailComment,
        newItem: CommunityPostDetailComment
    ): Boolean {
        return oldItem.commentId == newItem.commentId
    }

    override fun areContentsTheSame(
        oldItem: CommunityPostDetailComment,
        newItem: CommunityPostDetailComment
    ): Boolean {
        return oldItem == newItem
    }
}