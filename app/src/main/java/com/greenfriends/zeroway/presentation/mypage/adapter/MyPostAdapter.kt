package com.greenfriends.zeroway.presentation.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.greenfriends.zeroway.data.model.MyPostList
import com.greenfriends.zeroway.data.model.MyPostResponse
import com.greenfriends.zeroway.databinding.ItemMypagePostBinding
import com.greenfriends.zeroway.presentation.mypage.viewmodel.MyPageViewModel

class MyPostAdapter(
    private val viewModel: MyPageViewModel
) :
    ListAdapter<MyPostList, RecyclerView.ViewHolder>(diffUtil) {

    interface MyItemClickListener {
        fun onItemClick(post: MyPostList)
    }

    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        this.mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemMypagePostBinding =
            ItemMypagePostBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    inner class ViewHolder(val binding: ItemMypagePostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(post: MyPostList) {
            binding.viewModel = viewModel
            binding.myPost = post
            binding.mypostCl.setOnClickListener {
                mItemClickListener.onItemClick(post)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(getItem(position))
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<MyPostList>() {
            override fun areContentsTheSame(oldItem: MyPostList, newItem: MyPostList) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: MyPostList, newItem: MyPostList) =
                oldItem == newItem
        }
    }
}