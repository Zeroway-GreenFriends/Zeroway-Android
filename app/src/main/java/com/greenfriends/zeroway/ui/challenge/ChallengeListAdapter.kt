package com.greenfriends.zeroway.ui.challenge


import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.greenfriends.zeroway.R
import com.greenfriends.zeroway.databinding.ItemChallengeListBinding
import com.greenfriends.zeroway.model.ChallengeListResponse
import com.greenfriends.zeroway.model.TermResponse
import com.greenfriends.zeroway.ui.home.TermAdapter


class ChallengeListAdapter(
    private val viewModel: ChallengeViewModel
    ) :
    ListAdapter<ChallengeListResponse, RecyclerView.ViewHolder>(diffUtil) {

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

    inner class ViewHolder(val binding: ItemChallengeListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(challengeList: ChallengeListResponse) {

            binding.viewModel = viewModel
            binding.challengeList = challengeList
            binding.executePendingBindings()

            binding.challengeListListTv1.setOnClickListener {
                mItemClickListener.onItemClick(challengeList)
            }
//        }
//            when (challengeList.complete) {
//                true -> {
//                    binding.challengeListListTv1.setTextColor(Color.parseColor("#6FE0D6"))
//                    binding.challengeListCheckBtn1.setImageResource(R.drawable.ic_check_circle_mint)
//                }
//                false -> {
//                    binding.challengeListListTv1.setTextColor(Color.parseColor("#000000"))
//                    binding.challengeListCheckBtn1.setImageResource(R.drawable.ic_check_circle_gray)
//                }
//            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ChallengeListResponse>() {
            override fun areContentsTheSame(oldItem: ChallengeListResponse, newItem: ChallengeListResponse) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: ChallengeListResponse, newItem: ChallengeListResponse) =
                oldItem.challengeId == newItem.challengeId
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(getItem(position))

        //밑에 꺼 설정하면 클릭되는지 확인할 것
//        holder.itemView.setOnClickListener {
//            mItemClickListener.onItemClick(getItem(position))
//        }
    }


}