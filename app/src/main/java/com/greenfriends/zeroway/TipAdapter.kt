package com.greenfriends.zeroway


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.greenfriends.zeroway.databinding.ItemHomeShopBinding
import com.greenfriends.zeroway.databinding.ItemHomeTipBinding
import com.greenfriends.zeroway.databinding.ItemHomeWordBinding


class TipAdapter(private val tipList: ArrayList<TipList>) :
    RecyclerView.Adapter<TipAdapter.ViewHolder>() {

    interface MyItemClickListener {
        fun onItemClick(tip: TipList)
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
        holder.itemView.setOnClickListener {
            mItemClickListener.onItemClick(tipList[position])
        }
    }

    override fun getItemCount(): Int {
        return tipList.size
    }

    inner class ViewHolder(val binding: ItemHomeTipBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tip: TipList) {
            binding.itemHomeTipNumberTv.text = tip.number
            binding.itemHomeTipContentTv.text = tip.content
        }
    }

}