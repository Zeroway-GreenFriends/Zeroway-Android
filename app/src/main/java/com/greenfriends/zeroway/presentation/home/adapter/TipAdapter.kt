package com.greenfriends.zeroway.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.greenfriends.zeroway.data.model.TipResponse
import com.greenfriends.zeroway.databinding.ItemHomeTipBinding
import com.greenfriends.zeroway.presentation.home.viewmodel.HomeViewModel

class TipAdapter(
    private val viewModel: HomeViewModel
) :
    ListAdapter<TipResponse, RecyclerView.ViewHolder>(diffUtil) {

    interface MyItemClickListener {
        fun onItemClick(tip: TipResponse)
    }

    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemHomeTipBinding =
            ItemHomeTipBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    inner class ViewHolder(val binding: ItemHomeTipBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tip: TipResponse) {
            binding.viewModel = viewModel
            binding.tipResponse = tip
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(getItem(position))
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<TipResponse>() {
            override fun areContentsTheSame(oldItem: TipResponse, newItem: TipResponse) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: TipResponse, newItem: TipResponse) =
                oldItem == newItem
        }
    }
}