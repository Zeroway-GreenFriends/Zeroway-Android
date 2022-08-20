package com.greenfriends.zeroway.ui.communitypostdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.greenfriends.zeroway.databinding.ItemCommunityPostCommentBinding
import com.greenfriends.zeroway.model.CommunityPostDetailComment

class CommunityPostDetailCommentAdapter :
    ListAdapter<CommunityPostDetailComment, CommunityPostDetailCommentAdapter.CommunityPostDetailCommentViewHolder>(
        CommunityPostDetailCommentDiffCallback()
    ) {

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

    class CommunityPostDetailCommentViewHolder(private val binding: ItemCommunityPostCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(communityPostDetailComment: CommunityPostDetailComment) {
            binding.communityPostDetailComment = communityPostDetailComment
            binding.executePendingBindings()
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