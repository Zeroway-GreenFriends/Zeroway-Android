package com.greenfriends.zeroway.presentation.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.greenfriends.zeroway.data.model.NoticeResponse
import com.greenfriends.zeroway.databinding.ItemAlarmBinding
import com.greenfriends.zeroway.presentation.mypage.viewmodel.NoticeViewModel

class NoticeAdapter(
    private val viewModel: NoticeViewModel
) :
    ListAdapter<NoticeResponse, RecyclerView.ViewHolder>(diffUtil) {

    interface MyItemClickListener {
        fun onItemClick(notice: NoticeResponse)
    }

    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        this.mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemAlarmBinding =
            ItemAlarmBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    inner class ViewHolder(val binding: ItemAlarmBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(notice: NoticeResponse) {
            binding.viewModel = viewModel
            binding.noticeResponse = notice
            binding.itemAlarmCl.setOnClickListener {
                mItemClickListener.onItemClick(notice)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(getItem(position))
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<NoticeResponse>() {
            override fun areContentsTheSame(oldItem: NoticeResponse, newItem: NoticeResponse) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: NoticeResponse, newItem: NoticeResponse) =
                oldItem == newItem
        }
    }
}