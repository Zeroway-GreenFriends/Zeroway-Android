package com.greenfriends.zeroway.ui.community.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.greenfriends.zeroway.databinding.ItemCommunityPostImageBinding

class CommunityPostAdapter :
    RecyclerView.Adapter<CommunityPostAdapter.CommunityPostViewHolder>() {

    private val imageUrls = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityPostViewHolder {
        val binding = ItemCommunityPostImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CommunityPostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommunityPostViewHolder, position: Int) {
        holder.bind(imageUrls[position])
    }

    override fun getItemCount(): Int = imageUrls.size

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(imageUrls: List<String>) {
        if (imageUrls.isNotEmpty()) {
            this.imageUrls.clear()
        }
        this.imageUrls.addAll(imageUrls)
        notifyDataSetChanged()
    }

    class CommunityPostViewHolder(private val binding: ItemCommunityPostImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(imageUrl: String) {
            binding.imageUrl = imageUrl
            binding.executePendingBindings()
        }
    }
}