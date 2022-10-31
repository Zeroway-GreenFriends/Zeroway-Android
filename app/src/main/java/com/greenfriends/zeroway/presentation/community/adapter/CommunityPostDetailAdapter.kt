package com.greenfriends.zeroway.presentation.community.adapter

import android.content.Context
import android.os.Build
import android.view.ContextThemeWrapper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.greenfriends.zeroway.R
import com.greenfriends.zeroway.data.model.CommunityPostDetailResponse
import com.greenfriends.zeroway.databinding.ItemCommunityExcludeImagePostDetailBinding
import com.greenfriends.zeroway.databinding.ItemCommunityIncludeImagePostDetailBinding
import com.greenfriends.zeroway.presentation.community.OnCommunityPostDetailPostClickListener

class CommunityPostDetailAdapter(private val context: Context) :
    ListAdapter<CommunityPostDetailResponse, RecyclerView.ViewHolder>(
        CommunityPostDetailDiffCallback()
    ) {

    private lateinit var onCommunityPostDetailPostClickListener: OnCommunityPostDetailPostClickListener

    fun setOnCommunityPostDetailPostClickListener(onCommunityPostDetailPostClickListener: OnCommunityPostDetailPostClickListener) {
        this.onCommunityPostDetailPostClickListener = onCommunityPostDetailPostClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 1) {
            val binding = ItemCommunityIncludeImagePostDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            CommunityPostDetailIncludeImageViewHolder(binding)
        } else {
            val binding = ItemCommunityExcludeImagePostDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            CommunityPostDetailExcludeImageViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == 1) {
            (holder as CommunityPostDetailIncludeImageViewHolder).bind(
                getItem(position)
            )
        } else {
            (holder as CommunityPostDetailExcludeImageViewHolder).bind(
                getItem(position)
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).imageList.isNotEmpty()) 1 else 0
    }

    inner class CommunityPostDetailIncludeImageViewHolder(val binding: ItemCommunityIncludeImagePostDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val communityPostAdapter = CommunityPostAdapter()

        fun bind(communityPostDetailResponse: CommunityPostDetailResponse) {
            binding.itemCommunityIncludeImagePostDetailImageVp.adapter = communityPostAdapter
            communityPostAdapter.submitList(communityPostDetailResponse.imageList)
            binding.itemCommunityIncludeImagePostDetailImageSdi.setViewPager2(binding.itemCommunityIncludeImagePostDetailImageVp)
            binding.communityPostDetail = communityPostDetailResponse
            binding.executePendingBindings()

            with(binding) {
                itemCommunityIncludeImagePostDetailLikeIv.setOnClickListener {
                    communityPostDetailResponse.liked = !communityPostDetailResponse.liked
                    if (communityPostDetailResponse.liked) {
                        itemCommunityIncludeImagePostDetailLikeIv.setImageResource(R.drawable.ic_like_on)
                        ((itemCommunityIncludeImagePostDetailLikeCountTv.text.substring(0 until itemCommunityIncludeImagePostDetailLikeCountTv.text.length - 1)
                            .toInt() + 1).toString() + "개").also {
                            itemCommunityIncludeImagePostDetailLikeCountTv.text = it
                        }
                    } else {
                        itemCommunityIncludeImagePostDetailLikeIv.setImageResource(R.drawable.ic_like_off)
                        ((itemCommunityIncludeImagePostDetailLikeCountTv.text.substring(0 until itemCommunityIncludeImagePostDetailLikeCountTv.text.length - 1)
                            .toInt() - 1).toString() + "개").also {
                            itemCommunityIncludeImagePostDetailLikeCountTv.text = it
                        }
                    }
                    onCommunityPostDetailPostClickListener.setCommunityPostLike(
                        communityPostDetailResponse
                    )
                }

                itemCommunityIncludeImagePostDetailBookmarkIv.setOnClickListener {
                    communityPostDetailResponse.bookmarked = !communityPostDetailResponse.bookmarked
                    if (communityPostDetailResponse.bookmarked) {
                        itemCommunityIncludeImagePostDetailBookmarkIv.setImageResource(R.drawable.ic_bookmark_on)
                    } else {
                        itemCommunityIncludeImagePostDetailBookmarkIv.setImageResource(R.drawable.ic_bookmark_off)
                    }
                    onCommunityPostDetailPostClickListener.setCommunityPostBookmark(
                        communityPostDetailResponse
                    )
                }

                itemCommunityIncludeImagePostDetailSelectIv.setOnClickListener {
                    val wrapper =
                        ContextThemeWrapper(context, R.style.Widget_Material3_PopupMenu_Custom)
                    val popup = PopupMenu(wrapper, it)
                    popup.menuInflater.inflate(R.menu.menu_option_item, popup.menu)
                    popup.setOnMenuItemClickListener { menuItem ->
                        when (menuItem.itemId) {
                            R.id.menu_option_item_report -> {
                                onCommunityPostDetailPostClickListener.reportCommunityPost(
                                    communityPostDetailResponse
                                )
                            }
                            R.id.menu_option_item_delete -> {
                                onCommunityPostDetailPostClickListener.deleteCommunityPost(
                                    communityPostDetailResponse
                                )
                            }
                            else -> TODO()
                        }
                        false
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        popup.gravity = Gravity.END
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        popup.setForceShowIcon(true)
                    }
                    popup.show()
                }
            }
        }
    }

    inner class CommunityPostDetailExcludeImageViewHolder(val binding: ItemCommunityExcludeImagePostDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(communityPostDetailResponse: CommunityPostDetailResponse) {
            binding.communityPostDetail = communityPostDetailResponse
            binding.executePendingBindings()

            with(binding) {
                itemCommunityExcludeImagePostDetailLikeIv.setOnClickListener {
                    communityPostDetailResponse.liked = !communityPostDetailResponse.liked
                    if (communityPostDetailResponse.liked) {
                        itemCommunityExcludeImagePostDetailLikeIv.setImageResource(R.drawable.ic_like_on)
                        ((itemCommunityExcludeImagePostDetailLikeCountTv.text.substring(0 until itemCommunityExcludeImagePostDetailLikeCountTv.text.length - 1)
                            .toInt() + 1).toString() + "개").also {
                            itemCommunityExcludeImagePostDetailLikeCountTv.text = it
                        }
                    } else {
                        itemCommunityExcludeImagePostDetailLikeIv.setImageResource(R.drawable.ic_like_off)
                        ((itemCommunityExcludeImagePostDetailLikeCountTv.text.substring(0 until itemCommunityExcludeImagePostDetailLikeCountTv.text.length - 1)
                            .toInt() - 1).toString() + "개").also {
                            itemCommunityExcludeImagePostDetailLikeCountTv.text = it
                        }
                    }
                    onCommunityPostDetailPostClickListener.setCommunityPostLike(
                        communityPostDetailResponse
                    )
                }

                itemCommunityExcludeImagePostDetailBookmarkIv.setOnClickListener {
                    communityPostDetailResponse.bookmarked = !communityPostDetailResponse.bookmarked
                    if (communityPostDetailResponse.bookmarked) {
                        itemCommunityExcludeImagePostDetailBookmarkIv.setImageResource(R.drawable.ic_bookmark_on)
                    } else {
                        itemCommunityExcludeImagePostDetailBookmarkIv.setImageResource(R.drawable.ic_bookmark_off)
                    }
                    onCommunityPostDetailPostClickListener.setCommunityPostBookmark(
                        communityPostDetailResponse
                    )
                }

                itemCommunityExcludeImagePostDetailSelectIv.setOnClickListener {
                    val wrapper =
                        ContextThemeWrapper(context, R.style.Widget_Material3_PopupMenu_Custom)
                    val popup = PopupMenu(wrapper, it)
                    popup.menuInflater.inflate(R.menu.menu_option_item, popup.menu)
                    popup.setOnMenuItemClickListener { menuItem ->
                        when (menuItem.itemId) {
                            R.id.menu_option_item_report -> {
                                onCommunityPostDetailPostClickListener.reportCommunityPost(
                                    communityPostDetailResponse
                                )
                            }
                            R.id.menu_option_item_delete -> {
                                onCommunityPostDetailPostClickListener.deleteCommunityPost(
                                    communityPostDetailResponse
                                )
                            }
                            else -> TODO()
                        }
                        false
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        popup.gravity = Gravity.END
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        popup.setForceShowIcon(true)
                    }
                    popup.show()
                }
            }
        }
    }
}

class CommunityPostDetailDiffCallback : DiffUtil.ItemCallback<CommunityPostDetailResponse>() {

    override fun areItemsTheSame(
        oldItem: CommunityPostDetailResponse,
        newItem: CommunityPostDetailResponse
    ): Boolean {
        return oldItem.postId == newItem.postId
    }

    override fun areContentsTheSame(
        oldItem: CommunityPostDetailResponse,
        newItem: CommunityPostDetailResponse
    ): Boolean {
        return oldItem == newItem
    }
}