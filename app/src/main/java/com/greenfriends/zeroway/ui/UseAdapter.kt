package com.greenfriends.zeroway.ui


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.greenfriends.zeroway.model.UseList
import com.greenfriends.zeroway.databinding.ItemHomeUseBinding


class UseAdapter(private val useList: ArrayList<UseList>) :
    RecyclerView.Adapter<UseAdapter.ViewHolder>() {

    interface MyItemClickListener {
        fun onItemClick(use: UseList)
    }

    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemHomeUseBinding =
            ItemHomeUseBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            mItemClickListener.onItemClick(useList[position])
        }
    }

    override fun getItemCount(): Int {
        return useList.size
    }

    inner class ViewHolder(val binding: ItemHomeUseBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(use: UseList) {
            binding.itemHomeUseCount.text = use.count
            binding.itemHomeUseTitleTv.text = use.title
        }
    }

}