package com.greenfriends.zeroway.presentation.store.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.greenfriends.zeroway.data.model.StoreResponse
import com.greenfriends.zeroway.databinding.ItemLoadingBinding
import com.greenfriends.zeroway.databinding.ItemStorePostBinding
import com.greenfriends.zeroway.presentation.common.LoadingViewHolder
import com.greenfriends.zeroway.presentation.store.viewmodel.StoreViewModel

private const val VIEW_TYPE_LOADING = 0
private const val VIEW_TYPE_POST = 1

class StoreAdapter(private val viewModel: StoreViewModel) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val stores = mutableListOf<StoreResponse?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_LOADING -> LoadingViewHolder(
                ItemLoadingBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
            else -> StoreViewHolder(
                ItemStorePostBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is StoreViewHolder -> holder.bind(stores[position]!!)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (stores[position]) {
            null -> VIEW_TYPE_LOADING
            else -> VIEW_TYPE_POST
        }
    }

    override fun getItemCount(): Int = stores.size

    fun submitStores(stores: List<StoreResponse>) {
        this.stores.addAll(stores)
        notifyItemRangeInserted(itemCount, stores.size)
    }


    fun setLoading(isLoading: Boolean) {
        return when (isLoading) {
            true -> {
                stores.add(null)
                notifyItemInserted(itemCount - 1)
            }
            else -> {
                stores.removeAt(itemCount - 1)
                notifyItemRemoved(itemCount)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        stores.clear()
        notifyDataSetChanged()
    }

    inner class StoreViewHolder(private val binding: ItemStorePostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(storeResponse: StoreResponse) {
            binding.viewModel = viewModel
            binding.storeResponse = storeResponse
            binding.executePendingBindings()
        }
    }
}