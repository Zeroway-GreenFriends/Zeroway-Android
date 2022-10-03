package com.greenfriends.zeroway.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.greenfriends.zeroway.data.model.TermResponse
import com.greenfriends.zeroway.databinding.ItemHomeTermBinding
import com.greenfriends.zeroway.ui.home.viewmodel.HomeViewModel

class TermAdapter(
    private val viewModel: HomeViewModel
) :
    ListAdapter<TermResponse, RecyclerView.ViewHolder>(diffUtil) {

    interface MyItemClickListener {
        fun onItemClick(word: TermResponse)
    }

    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        this.mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemHomeTermBinding =
            ItemHomeTermBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    inner class ViewHolder(val binding: ItemHomeTermBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(word: TermResponse) {
            binding.viewModel = viewModel
            binding.termResponse = word
            binding.itemHomeTermCl.setOnClickListener {
                mItemClickListener.onItemClick(word)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(getItem(position))
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<TermResponse>() {
            override fun areContentsTheSame(oldItem: TermResponse, newItem: TermResponse) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: TermResponse, newItem: TermResponse) =
                oldItem == newItem
        }
    }
}