package com.greenfriends.zeroway.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.greenfriends.zeroway.data.model.TermResponse
import com.greenfriends.zeroway.databinding.ItemTermSearchBinding
import com.greenfriends.zeroway.presentation.home.viewmodel.HomeViewModel

class TermSearchAdapter(
    private val viewModel: HomeViewModel
) :
    ListAdapter<TermResponse, RecyclerView.ViewHolder>(diffUtil) {

    interface MyItemClickListener {
        fun onItemClick(word: TermResponse)
    }

    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemTermSearchBinding =
            ItemTermSearchBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    inner class ViewHolder(val binding: ItemTermSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(word: TermResponse) {
            binding.viewModel = viewModel
            binding.termResponse = word
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<TermResponse>() {
            override fun areContentsTheSame(oldItem: TermResponse, newItem: TermResponse) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: TermResponse, newItem: TermResponse) =
                oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(getItem(position))

        holder.itemView.setOnClickListener {
            mItemClickListener.onItemClick(getItem(position))
        }
    }
}