package com.greenfriends.zeroway.ui.challenge


import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.greenfriends.zeroway.R
import com.greenfriends.zeroway.databinding.ItemChallengeListBinding
import com.greenfriends.zeroway.model.TermResponse
import com.greenfriends.zeroway.databinding.ItemWordSearchBinding
import com.greenfriends.zeroway.model.ChallengeListResponse


class ChallengeListAdapter(private val challengeList: ArrayList<ChallengeListResponse>) :
    RecyclerView.Adapter<ChallengeListAdapter.ViewHolder>() {

    interface MyItemClickListener {
        fun onItemClick(challengeList: ChallengeListResponse)
    }

    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemChallengeListBinding =
            ItemChallengeListBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(challengeList[position])
        holder.itemView.setOnClickListener {
            mItemClickListener.onItemClick(challengeList[position])
        }
    }

    override fun getItemCount(): Int {
        return challengeList.size
    }

    inner class ViewHolder(val binding: ItemChallengeListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(challengeList: ChallengeListResponse) {

            binding.challengeListListTv1.text = challengeList.content

            when (challengeList.complete) {
                true -> {
                    binding.challengeListListTv1.setTextColor(Color.parseColor("#6FE0D6"))
                    binding.challengeListCheckBtn1.setImageResource(R.drawable.ic_check_circle_mint)
                }
                false -> {
                    binding.challengeListListTv1.setTextColor(Color.parseColor("#000000"))
                    binding.challengeListCheckBtn1.setImageResource(R.drawable.ic_check_circle_gray)
                }
            }
        }
    }

}