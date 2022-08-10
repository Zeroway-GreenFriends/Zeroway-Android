package com.greenfriends.zeroway


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.greenfriends.zeroway.data.TipResponse
import com.greenfriends.zeroway.databinding.ItemHomeTipBinding


class TipAdapter(private val tipList: ArrayList<TipResponse>) :
    RecyclerView.Adapter<TipAdapter.ViewHolder>() {

    interface MyItemClickListener {
        fun onItemClick(tip: TipResponse)
    }

    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TipAdapter.ViewHolder {
        val binding: ItemHomeTipBinding =
            ItemHomeTipBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TipAdapter.ViewHolder, position: Int) {
        holder.bind(tipList[position])
        holder.itemView.setOnClickListener {
            mItemClickListener.onItemClick(tipList[position])
        }
    }

    override fun getItemCount(): Int {
        return tipList.size
    }

    inner class ViewHolder(val binding: ItemHomeTipBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tip: TipResponse) {
            binding.itemHomeTipNumberTv.text = tip.num
            binding.itemHomeTipTitleTv.text = tip.title
            binding.itemHomeTipContentTv.text = tip.content
        }
    }

}