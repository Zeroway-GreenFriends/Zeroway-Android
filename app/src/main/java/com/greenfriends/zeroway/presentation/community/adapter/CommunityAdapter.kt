package com.greenfriends.zeroway.presentation.community.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.ContextThemeWrapper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.greenfriends.zeroway.R
import com.greenfriends.zeroway.data.model.CommunityPost
import com.greenfriends.zeroway.databinding.ItemCommunityExcludeImagePostBinding
import com.greenfriends.zeroway.databinding.ItemCommunityIncludeImagePostBinding
import com.greenfriends.zeroway.databinding.ItemLoadingBinding
import com.greenfriends.zeroway.presentation.common.LoadingViewHolder
import com.greenfriends.zeroway.presentation.community.OnCommunityItemClickListener
import com.greenfriends.zeroway.presentation.community.viewmodel.CommunityViewModel

private const val VIEW_TYPE_LOADING = 0
private const val VIEW_TYPE_IMAGE_POST = 1
private const val VIEW_TYPE_POST = 2

class CommunityAdapter(
    private val viewModel: CommunityViewModel,
    private val context: Context
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val communityPosts = mutableListOf<CommunityPost?>()
    private lateinit var onCommunityItemClickListener: OnCommunityItemClickListener

    fun setOnCommunityItemClickListener(onCommunityItemClickListener: OnCommunityItemClickListener) {
        this.onCommunityItemClickListener = onCommunityItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_LOADING -> {
                LoadingViewHolder(
                    ItemLoadingBinding.inflate(inflater, parent, false)
                )
            }
            VIEW_TYPE_IMAGE_POST -> {
                CommunityIncludeImageViewHolder(
                    ItemCommunityIncludeImagePostBinding.inflate(inflater, parent, false)
                )
            }
            else -> {
                CommunityExcludeImageViewHolder(
                    ItemCommunityExcludeImagePostBinding.inflate(inflater, parent, false)
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CommunityIncludeImageViewHolder -> holder.bind(communityPosts[position]!!)
            is CommunityExcludeImageViewHolder -> holder.bind(communityPosts[position]!!)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (communityPosts[position]) {
            null -> VIEW_TYPE_LOADING
            else -> {
                if (communityPosts[position]!!.imageList.isNotEmpty()) VIEW_TYPE_IMAGE_POST else VIEW_TYPE_POST
            }
        }
    }

    override fun getItemCount(): Int = communityPosts.size

    fun submitCommunityPosts(communityPosts: List<CommunityPost>) {
        this.communityPosts.addAll(communityPosts)
        notifyItemRangeInserted(itemCount, communityPosts.size)
    }

    fun setLoading(isLoading: Boolean) {
        when (isLoading) {
            true -> {
                communityPosts.add(null)
                notifyItemInserted(itemCount - 1)
            }
            else -> {
                communityPosts.removeAt(itemCount - 1)
                notifyItemRemoved(itemCount)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        communityPosts.clear()
        notifyDataSetChanged()
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
                    popup.menuInflater.inflate(R.menu.menu_option_report_item, popup.menu)
                    popup.setOnMenuItemClickListener { menuItem ->
                        when (menuItem.itemId) {
                            R.id.menu_option_report_item_report -> {
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
                    popup.menuInflater.inflate(R.menu.menu_option_report_item, popup.menu)
                    popup.setOnMenuItemClickListener { menuItem ->
                        when (menuItem.itemId) {
                            R.id.menu_option_report_item_report -> {
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