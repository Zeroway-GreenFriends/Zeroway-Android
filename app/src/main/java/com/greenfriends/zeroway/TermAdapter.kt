package com.greenfriends.zeroway


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.greenfriends.zeroway.data.TermResponse
import com.greenfriends.zeroway.databinding.ItemHomeTermBinding

class TermAdapter(private val termList: ArrayList<TermResponse>) :
    RecyclerView.Adapter<TermAdapter.ViewHolder>() {

    interface MyItemClickListener {
        fun onItemClick(word: TermResponse)
    }

    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TermAdapter.ViewHolder {
        val binding: ItemHomeTermBinding =
            ItemHomeTermBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TermAdapter.ViewHolder, position: Int) {
        holder.bind(termList[position])
        holder.itemView.setOnClickListener {
            mItemClickListener.onItemClick(termList[position])
        }
    }

    override fun getItemCount(): Int {
        return termList.size
    }

    inner class ViewHolder(val binding: ItemHomeTermBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(term: TermResponse) {
            binding.itemHomeTermTitleTv.text = term.term
            binding.itemHomneTermContentTv.text = term.description
        }
    }

}