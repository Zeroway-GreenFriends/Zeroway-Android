package com.greenfriends.zeroway.ui.home.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.greenfriends.zeroway.data.model.StoreResponse
import com.greenfriends.zeroway.databinding.ItemHomeShopBinding


class HomeStoreAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val stores = mutableListOf<StoreResponse?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return StoreViewHolder(
            ItemHomeShopBinding.inflate(
                inflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is StoreViewHolder -> holder.bind(stores[position]!!)
        }
    }


    override fun getItemCount(): Int = stores.size

    fun submitStores(stores: List<StoreResponse>) {
        this.stores.addAll(stores)
        notifyItemRangeInserted(itemCount, stores.size)
    }


    class StoreViewHolder(private val binding: ItemHomeShopBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(storeResponse: StoreResponse) {
            binding.storeResponse = storeResponse
            binding.executePendingBindings()
        }
    }
}