package com.greenfriends.zeroway.ui.communitypostdetail

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
import com.greenfriends.zeroway.common.POST_ID
import com.greenfriends.zeroway.databinding.FragmentCommunityPostDetailBinding
import com.greenfriends.zeroway.model.CommunityPostDetailResponse
import com.greenfriends.zeroway.ui.common.EventObserve
import com.greenfriends.zeroway.ui.common.ViewModelFactory
import com.greenfriends.zeroway.ui.community.CommunityFragment

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

        communityPostDetailAdapter.setOnCommunityPostDetailItemClickListener(object :
            OnCommunityPostDetailItemClickListener {

            override fun deleteCommunityPost(communityPostDetail: CommunityPostDetailResponse) {
                viewModel.deletePost(
                    getJwt()!!,
                    communityPostDetail.postId.toString()
                )
            }

            override fun setCommunityPostLike(communityPostDetail: CommunityPostDetailResponse) {
                viewModel.setPostLike(
                    getJwt()!!,
                    communityPostDetail.postId.toString(),
                    communityPostDetail.liked
                )
            }

            override fun setCommunityPostBookmark(communityPostDetail: CommunityPostDetailResponse) {
                viewModel.setPostBookmark(
                    getJwt()!!,
                    communityPostDetail.postId.toString(),
                    communityPostDetail.bookmarked
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