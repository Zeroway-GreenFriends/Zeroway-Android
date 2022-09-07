package com.greenfriends.zeroway.ui.store.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.greenfriends.zeroway.data.model.StoreResponse
import com.greenfriends.zeroway.databinding.ItemStorePostBinding

class StoreAdapter : RecyclerView.Adapter<StoreAdapter.StoreViewHolder>() {

    private val stores = mutableListOf<StoreResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val binding =
            ItemStorePostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        holder.bind(stores[position])
    }

    override fun getItemCount(): Int = stores.size

    fun submitStores(stores: List<StoreResponse>) {
        this.stores.addAll(stores)
        notifyItemRangeInserted(this.stores.size, stores.size)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        stores.clear()
        notifyDataSetChanged()
    }

    class StoreViewHolder(private val binding: ItemStorePostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(storeResponse: StoreResponse) {
            binding.storeResponse = storeResponse
            binding.executePendingBindings()
        }
    }
}