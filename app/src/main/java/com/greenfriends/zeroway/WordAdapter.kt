package com.greenfriends.zeroway


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.greenfriends.zeroway.databinding.ItemHomeWordBinding


class WordAdapter(private val wordList: ArrayList<WordList>) :
    RecyclerView.Adapter<WordAdapter.ViewHolder>() {

    interface MyItemClickListener {
        fun onItemClick(word: WordList)
    }

    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): WordAdapter.ViewHolder {
        val binding: ItemHomeWordBinding =
            ItemHomeWordBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WordAdapter.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            mItemClickListener.onItemClick(wordList[position])
        }
    }

    override fun getItemCount(): Int {
        return wordList.size
    }

    inner class ViewHolder(val binding: ItemHomeWordBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(word: WordList) {
            binding.itemHomeWordTitleTv.text = word.title
            binding.itemHomeWordTitleEngTv.text = word.engTitle
            binding.itemHomneWordContent.text = word.content
        }
    }

}