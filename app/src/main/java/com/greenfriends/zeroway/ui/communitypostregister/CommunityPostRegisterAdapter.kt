package com.greenfriends.zeroway.ui.communitypostregister

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.greenfriends.zeroway.databinding.ItemCommunityPostRegisterImageBinding

class CommunityPostRegisterAdapter :
    ListAdapter<String, CommunityPostRegisterAdapter.CommunityPostRegisterViewHolder>(
        CommunityPostRegisterDiffCallback()
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommunityPostRegisterViewHolder {
        val binding = ItemCommunityPostRegisterImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CommunityPostRegisterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommunityPostRegisterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CommunityPostRegisterViewHolder(private val binding: ItemCommunityPostRegisterImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(imageUrl: String) {

            binding.imageUrl = imageUrl
            binding.executePendingBindings()
        }
    }
}

class CommunityPostRegisterDiffCallback : DiffUtil.ItemCallback<String>() {

    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}