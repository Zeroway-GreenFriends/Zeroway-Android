package com.greenfriends.zeroway.ui.community.adapter

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
import com.greenfriends.zeroway.data.model.CommunityPost
import com.greenfriends.zeroway.databinding.ItemCommunityExcludeImagePostBinding
import com.greenfriends.zeroway.databinding.ItemCommunityIncludeImagePostBinding
import com.greenfriends.zeroway.ui.community.OnCommunityItemClickListener
import com.greenfriends.zeroway.ui.community.viewmodel.CommunityViewModel

class CommunityAdapter(
    private val viewModel: CommunityViewModel,
    private val context: Context
) :
    ListAdapter<CommunityPost, RecyclerView.ViewHolder>(CommunityDiffCallback()) {

    private lateinit var onCommunityItemClickListener: OnCommunityItemClickListener

    fun setOnCommunityItemClickListener(onCommunityItemClickListener: OnCommunityItemClickListener) {
        this.onCommunityItemClickListener = onCommunityItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 1) {
            val binding = ItemCommunityIncludeImagePostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            CommunityIncludeImageViewHolder(binding)
        } else {
            val binding = ItemCommunityExcludeImagePostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            CommunityExcludeImageViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == 1) {
            (holder as CommunityIncludeImageViewHolder).bind(getItem(position))
        } else {
            (holder as CommunityExcludeImageViewHolder).bind(getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).imageList.isNotEmpty()) 1 else 0
    }

    inner class CommunityIncludeImageViewHolder(val binding: ItemCommunityIncludeImagePostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val communityPostAdapter = CommunityPostAdapter()

        fun bind(communityPost: CommunityPost) {
            binding.itemCommunityIncludeImageImageVp.adapter = communityPostAdapter
            communityPostAdapter.submitList(communityPost.imageList)
            binding.itemCommunityIncludeImageImageSdi.setViewPager2(binding.itemCommunityIncludeImageImageVp)
            binding.viewModel = viewModel
            binding.communityPost = communityPost
            binding.executePendingBindings()

            with(binding) {
                itemCommunityIncludeImageLikeIv.setOnClickListener {
                    communityPost.liked = !communityPost.liked
                    if (communityPost.liked) {
                        itemCommunityIncludeImageLikeIv.setImageResource(R.drawable.ic_like_on)
                        ((itemCommunityIncludeImageLikeCountTv.text.substring(0 until itemCommunityIncludeImageLikeCountTv.text.length - 1)
                            .toInt() + 1).toString() + "개").also {
                            itemCommunityIncludeImageLikeCountTv.text = it
                        }
                    } else {
                        itemCommunityIncludeImageLikeIv.setImageResource(R.drawable.ic_like_off)
                        ((itemCommunityIncludeImageLikeCountTv.text.substring(0 until itemCommunityIncludeImageLikeCountTv.text.length - 1)
                            .toInt() - 1).toString() + "개").also {
                            itemCommunityIncludeImageLikeCountTv.text = it
                        }
                    }
                    onCommunityItemClickListener.setCommunityPostLike(communityPost)
                }

                itemCommunityIncludeImageBookmarkIv.setOnClickListener {
                    communityPost.bookmarked = !communityPost.bookmarked
                    if (communityPost.bookmarked) {
                        itemCommunityIncludeImageBookmarkIv.setImageResource(R.drawable.ic_bookmark_on)
                    } else {
                        itemCommunityIncludeImageBookmarkIv.setImageResource(R.drawable.ic_bookmark_off)
                    }
                    onCommunityItemClickListener.setCommunityPostBookmark(communityPost)
                }

                itemCommunityIncludeImageSelectIv.setOnClickListener {
                    val wrapper =
                        ContextThemeWrapper(context, R.style.Widget_Material3_PopupMenu_Custom)
                    val popup = PopupMenu(wrapper, it)
                    popup.menuInflater.inflate(R.menu.menu_option_item, popup.menu)
                    popup.setOnMenuItemClickListener { menuItem ->
                        when (menuItem.itemId) {
                            R.id.menu_option_item_delete -> {
                                onCommunityItemClickListener.deleteCommunityPost(communityPost)
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

    inner class CommunityExcludeImageViewHolder(val binding: ItemCommunityExcludeImagePostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(communityPost: CommunityPost) {
            binding.viewModel = viewModel
            binding.communityPost = communityPost
            binding.executePendingBindings()

            with(binding) {
                itemCommunityExcludeImageLikeIv.setOnClickListener {
                    communityPost.liked = !communityPost.liked
                    if (communityPost.liked) {
                        itemCommunityExcludeImageLikeIv.setImageResource(R.drawable.ic_like_on)
                        ((itemCommunityExcludeImageLikeCountTv.text.substring(0 until itemCommunityExcludeImageLikeCountTv.text.length - 1)
                            .toInt() + 1).toString() + "개").also {
                            itemCommunityExcludeImageLikeCountTv.text = it
                        }
                    } else {
                        itemCommunityExcludeImageLikeIv.setImageResource(R.drawable.ic_like_off)
                        ((itemCommunityExcludeImageLikeCountTv.text.substring(0 until itemCommunityExcludeImageLikeCountTv.text.length - 1)
                            .toInt() - 1).toString() + "개").also {
                            itemCommunityExcludeImageLikeCountTv.text = it
                        }
                    }
                    onCommunityItemClickListener.setCommunityPostLike(communityPost)
                }

                itemCommunityExcludeImageBookmarkIv.setOnClickListener {
                    communityPost.bookmarked = !communityPost.bookmarked
                    if (communityPost.bookmarked) {
                        itemCommunityExcludeImageBookmarkIv.setImageResource(R.drawable.ic_bookmark_on)
                    } else {
                        itemCommunityExcludeImageBookmarkIv.setImageResource(R.drawable.ic_bookmark_off)
                    }
                    onCommunityItemClickListener.setCommunityPostBookmark(communityPost)
                }

                itemCommunityExcludeImageSelectIv.setOnClickListener {
                    val wrapper =
                        ContextThemeWrapper(context, R.style.Widget_Material3_PopupMenu_Custom)
                    val popup = PopupMenu(wrapper, it)
                    popup.menuInflater.inflate(R.menu.menu_option_item, popup.menu)
                    popup.setOnMenuItemClickListener { menuItem ->
                        when (menuItem.itemId) {
                            R.id.menu_option_item_delete -> {
                                onCommunityItemClickListener.deleteCommunityPost(communityPost)
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

class CommunityDiffCallback : DiffUtil.ItemCallback<CommunityPost>() {

    override fun areItemsTheSame(oldItem: CommunityPost, newItem: CommunityPost): Boolean {
        return oldItem.postId == newItem.postId
    }

    override fun areContentsTheSame(oldItem: CommunityPost, newItem: CommunityPost): Boolean {
        return oldItem == newItem
    }
}