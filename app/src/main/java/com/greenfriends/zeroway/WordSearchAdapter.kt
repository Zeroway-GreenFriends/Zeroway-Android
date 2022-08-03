package com.greenfriends.zeroway


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.greenfriends.zeroway.databinding.ItemHomeWordBinding
import com.greenfriends.zeroway.databinding.ItemWordSearchBinding


class WordSearchAdapter(private val wordList: ArrayList<WordSearchList>) :
    RecyclerView.Adapter<WordSearchAdapter.ViewHolder>() {

    interface MyItemClickListener {
        fun onItemClick(word: WordSearchList)
    }

    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): WordSearchAdapter.ViewHolder {
        val binding: ItemWordSearchBinding =
            ItemWordSearchBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WordSearchAdapter.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            mItemClickListener.onItemClick(wordList[position])
        }
    }

    override fun getItemCount(): Int {
        return wordList.size
    }

    inner class ViewHolder(val binding: ItemWordSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(word: WordSearchList) {
            binding.itemWordSearchTitleTv.text = word.content
        }
    }

}