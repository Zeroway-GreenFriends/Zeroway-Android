package com.greenfriends.zeroway


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.greenfriends.zeroway.databinding.ItemHomeShopBinding
import com.greenfriends.zeroway.databinding.ItemHomeWordBinding


class ShopAdapter(private val shopList: ArrayList<ShopList>) :
    RecyclerView.Adapter<ShopAdapter.ViewHolder>() {

    interface MyItemClickListener {
        fun onItemClick(shop: ShopList)
    }

    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ShopAdapter.ViewHolder {
        val binding: ItemHomeShopBinding =
            ItemHomeShopBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShopAdapter.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            mItemClickListener.onItemClick(shopList[position])
        }
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    inner class ViewHolder(val binding: ItemHomeShopBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(shop: ShopList) {
            binding.itemHomeShopTitleTv.text = shop.title
            binding.itemHomeShopScoreTv.text = shop.score
            binding.itemHomeShopCountTv.text = shop.count
        }
    }

}