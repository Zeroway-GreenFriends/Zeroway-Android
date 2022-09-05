package com.greenfriends.zeroway.ui.home


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.greenfriends.zeroway.databinding.ItemHomeTermBinding
import com.greenfriends.zeroway.data.model.TermResponse


class TermAdapter(private val wordList: ArrayList<TermResponse>) :
    RecyclerView.Adapter<TermAdapter.ViewHolder>() {

    interface MyItemClickListener {
        fun onItemClick(word: TermResponse)
    }

    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding:  ItemHomeTermBinding=
            ItemHomeTermBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(wordList[position])
        holder.itemView.setOnClickListener {
            mItemClickListener.onItemClick(wordList[position])
        }
    }

    override fun getItemCount(): Int {
        return wordList.size
    }

    inner class ViewHolder(val binding: ItemHomeTermBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(word: TermResponse) {
            binding.itemHomeTermTitleTv.text = word.term
            binding.itemHomneTermContentTv.text = word.description
        }
    }

}