package com.greenfriends.zeroway.ui.home


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.greenfriends.zeroway.data.model.ShopList
import com.greenfriends.zeroway.databinding.ItemHomeShopBinding


class ShopAdapter(private val shopList: ArrayList<ShopList>) :
    RecyclerView.Adapter<ShopAdapter.ViewHolder>() {

    interface MyItemClickListener {
        fun onItemClick(shop: ShopList)
    }

    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemHomeShopBinding =
            ItemHomeShopBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(shopList[position])
//        holder.itemView.setOnClickListener {
//            mItemClickListener.onItemClick(shopList[position])
//        }
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    inner class ViewHolder(val binding: ItemHomeShopBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(shop: ShopList) {
            binding.itemHomeShopTitleTv.text = shop.title
//            binding.itemHomeShopScoreTv.text = shop.score
//            binding.itemHomeShopCountTv.text = shop.count

            Glide.with(binding.itemHomeShopImg.context)
                .load(shop.imgUrl)
                .into(binding.itemHomeShopImg)
        }
    }

}