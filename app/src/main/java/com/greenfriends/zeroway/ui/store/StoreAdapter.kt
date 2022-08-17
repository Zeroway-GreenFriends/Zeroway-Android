package com.greenfriends.zeroway.ui.store


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.greenfriends.zeroway.databinding.ItemHomeTipBinding
import com.greenfriends.zeroway.databinding.ItemStoreListBinding
import com.greenfriends.zeroway.model.StoreResponse
import com.greenfriends.zeroway.model.TipResponse


class StoreAdapter(private val storeList: ArrayList<StoreResponse>) :
    RecyclerView.Adapter<StoreAdapter.ViewHolder>() {

    interface MyItemClickListener {
        fun onItemClick(store: StoreResponse)
    }

    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemStoreListBinding =
            ItemStoreListBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(storeList[position])
        holder.itemView.setOnClickListener {
            mItemClickListener.onItemClick(storeList[position])
        }
    }

    override fun getItemCount(): Int {
        return storeList.size
    }

    inner class ViewHolder(val binding: ItemStoreListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(store: StoreResponse) {
            binding.itemStoreListAddressTv.text = store.addressNew
            binding.itemStoreListPhoneTv.text = store.contact
            binding.itemStoreListScoreTv.text = store.scoreAvg.toString()
            binding.itemStoreListTimeTv.text = store.operatingTime
            binding.itemStoreListTitleTv.text = store.name
            binding.itemStoreListUrlTv.text = store.siteUrl
            Glide.with(binding.itemStoreListImg.context)
                .load(store.imageUrl)
                .into(binding.itemStoreListImg)
        }
    }

}