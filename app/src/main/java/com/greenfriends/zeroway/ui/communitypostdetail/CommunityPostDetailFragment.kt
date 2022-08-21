package com.greenfriends.zeroway.ui.communitypostdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.greenfriends.zeroway.databinding.FragmentCommunityPostDetailBinding
import com.greenfriends.zeroway.model.CommunityPostDetailResponse
import com.greenfriends.zeroway.ui.common.EventObserve
import com.greenfriends.zeroway.ui.common.ViewModelFactory

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
        setObserve()
        setOnClickListener()
        setCommunityPostDetailAdapter()
        getPostDetail()
    }

    private fun setPostId() {
        arguments?.getString("postId")?.let { viewModel.setPostId(it) }
    }

    /**
     *  로그인 / 회원 가입 API에서 JWT, userProfileImg를 받아 와야 할 것 같다. 추후 수정 필요
     *
     * private fun setUserProfileImg() {
    binding.communityPostDetailCommentProfileIv
    }
     */

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
    }

    private fun setOnClickListener() {
        binding.communityPostDetailCommentRegisterTv.setOnClickListener {
            viewModel.setCommentRegisterEvent(binding.communityPostDetailCommentEt.text.toString())
        }
    }

    private fun setCommunityPostDetailAdapter() {
        communityPostDetailAdapter = CommunityPostDetailAdapter()
        communityPostDetailCommentsAdapter = CommunityPostDetailCommentsAdapter()
        binding.communityPostDetailRv.adapter =
            ConcatAdapter(communityPostDetailAdapter, communityPostDetailCommentsAdapter)

        communityPostDetailAdapter.setOnCommunityPostDetailItemClickListener(object :
            OnCommunityPostDetailItemClickListener {

            override fun deleteCommunityPost() {
                TODO("Not yet implemented")
            }

            override fun setCommunityPostLike(communityPostDetail: CommunityPostDetailResponse) {
                viewModel.setPostLike(
                    getJwt()!!,
                    communityPostDetail.postId.toString(),
                    communityPostDetail.liked
                )
            }

            override fun setCommunityPostBookmark(communityPostDetail: CommunityPostDetailResponse) {
                TODO("Not yet implemented")
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
}