package com.greenfriends.zeroway.ui.community.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.greenfriends.zeroway.GlideApp
import com.greenfriends.zeroway.R
import com.greenfriends.zeroway.POST_ID
import com.greenfriends.zeroway.databinding.FragmentCommunityPostDetailBinding
import com.greenfriends.zeroway.data.model.CommunityPostDetailComment
import com.greenfriends.zeroway.data.model.CommunityPostDetailResponse
import com.greenfriends.zeroway.ui.common.EventObserve
import com.greenfriends.zeroway.ui.common.ViewModelFactory
import com.greenfriends.zeroway.ui.community.adapter.CommunityPostDetailAdapter
import com.greenfriends.zeroway.ui.community.adapter.CommunityPostDetailCommentsAdapter
import com.greenfriends.zeroway.ui.community.OnCommunityPostDetailCommentClickListener
import com.greenfriends.zeroway.ui.community.OnCommunityPostDetailPostClickListener
import com.greenfriends.zeroway.ui.community.viewmodel.CommunityPostDetailViewModel

class CommunityPostDetailFragment : Fragment() {

    private val viewModel: CommunityPostDetailViewModel by viewModels { ViewModelFactory() }
    private lateinit var binding: FragmentCommunityPostDetailBinding
    private lateinit var communityPostDetailAdapter: CommunityPostDetailAdapter
    private lateinit var communityPostDetailCommentsAdapter: CommunityPostDetailCommentsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCommunityPostDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner

        setPostId()
        setUserProfileImg()
        setObserve()
        setOnClickListener()
        setCommunityPostDetailAdapter()
        getPostDetail()
    }

    private fun setPostId() {
        arguments?.getString(POST_ID)?.let { viewModel.setPostId(it) }
    }

    private fun setUserProfileImg() {
        GlideApp.with(this)
            .load(getProfileImgUrl())
            .into(binding.communityPostDetailCommentProfileIv)
    }

    private fun setObserve() {
        viewModel.communityPostDetailResponse.observe(
            viewLifecycleOwner
        ) { communityPostDetailResponse ->
            communityPostDetailAdapter.submitList(listOf(communityPostDetailResponse))
            communityPostDetailCommentsAdapter.submitList(listOf(communityPostDetailResponse))
        }

        viewModel.commentRegisterEvent.observe(
            viewLifecycleOwner, EventObserve {
                viewModel.setPostComment(getJwt()!!, viewModel.getPostId()!!, it)
            }
        )

        viewModel.communityPostDetailDeleteEvent.observe(
            viewLifecycleOwner, EventObserve { isDeleted ->
                if (isDeleted) {
                    Toast.makeText(requireContext(), "게시물이 삭제되었습니다.", Toast.LENGTH_SHORT).show()
                    startCommunityFragment()
                } else {
                    Toast.makeText(requireContext(), "해당 게시물 삭제 권한이 없습니다.", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        )
    }

    private fun setOnClickListener() {
        binding.communityPostDetailCommentRegisterTv.setOnClickListener {
            viewModel.setCommentRegisterEvent(binding.communityPostDetailCommentEt.text.toString())
        }
    }

    private fun setCommunityPostDetailAdapter() {
        communityPostDetailAdapter = CommunityPostDetailAdapter(requireContext())
        communityPostDetailCommentsAdapter = CommunityPostDetailCommentsAdapter()
        binding.communityPostDetailRv.adapter =
            ConcatAdapter(communityPostDetailAdapter, communityPostDetailCommentsAdapter)

        communityPostDetailAdapter.setOnCommunityPostDetailPostClickListener(object :
            OnCommunityPostDetailPostClickListener {

            override fun deleteCommunityPost(communityPostDetailResponse: CommunityPostDetailResponse) {
                viewModel.deletePost(
                    getJwt()!!,
                    communityPostDetailResponse.postId.toString()
                )
            }

            override fun setCommunityPostLike(communityPostDetailResponse: CommunityPostDetailResponse) {
                viewModel.setPostLike(
                    getJwt()!!,
                    communityPostDetailResponse.postId.toString(),
                    communityPostDetailResponse.liked
                )
            }

            override fun setCommunityPostBookmark(communityPostDetailResponse: CommunityPostDetailResponse) {
                viewModel.setPostBookmark(
                    getJwt()!!,
                    communityPostDetailResponse.postId.toString(),
                    communityPostDetailResponse.bookmarked
                )
            }
        })

        communityPostDetailCommentsAdapter.setOnCommunityPostDetailCommentClickListener(object :
            OnCommunityPostDetailCommentClickListener {

            override fun deleteCommunityPostComment(communityPostDetailComment: CommunityPostDetailComment) {
                TODO("Not yet implemented")
            }

            override fun setCommunityPostCommentLike(communityPostDetailComment: CommunityPostDetailComment) {
                viewModel.setPostCommentLike(
                    getJwt()!!,
                    communityPostDetailComment.commentId.toString(),
                    communityPostDetailComment.liked
                )
            }
        })
    }

    private fun getPostDetail() {
        viewModel.getPostDetail(getJwt()!!, viewModel.getPostId()!!)
    }

    private fun getJwt(): String? {
        val sharedPreferences =
            activity?.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return sharedPreferences!!.getString("jwt", null)
    }

    private fun getProfileImgUrl(): String? {
        val sharedPreferences =
            activity?.getSharedPreferences("profile", AppCompatActivity.MODE_PRIVATE)
        return sharedPreferences!!.getString("profileImgUrl", null)
    }

    private fun startCommunityFragment() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.main_fl, CommunityFragment())
            .commit()
    }
}