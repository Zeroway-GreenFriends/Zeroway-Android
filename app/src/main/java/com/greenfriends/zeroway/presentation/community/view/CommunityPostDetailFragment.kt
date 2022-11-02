package com.greenfriends.zeroway.presentation.community.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.greenfriends.zeroway.R
import com.greenfriends.zeroway.data.model.CommunityPostDetailComment
import com.greenfriends.zeroway.data.model.CommunityPostDetailResponse
import com.greenfriends.zeroway.data.model.CommunityReportRequest
import com.greenfriends.zeroway.databinding.FragmentCommunityPostDetailBinding
import com.greenfriends.zeroway.presentation.common.ConfirmDialogFragment
import com.greenfriends.zeroway.presentation.common.OnConfirmDialogClickListener
import com.greenfriends.zeroway.presentation.common.ViewModelFactory
import com.greenfriends.zeroway.presentation.community.OnCommunityPostDetailCommentClickListener
import com.greenfriends.zeroway.presentation.community.OnCommunityPostDetailPostClickListener
import com.greenfriends.zeroway.presentation.community.OnReportDialogClickListener
import com.greenfriends.zeroway.presentation.community.adapter.CommunityPostDetailAdapter
import com.greenfriends.zeroway.presentation.community.adapter.CommunityPostDetailCommentsAdapter
import com.greenfriends.zeroway.presentation.community.viewmodel.CommunityPostDetailViewModel
import com.greenfriends.zeroway.util.EventObserver
import com.greenfriends.zeroway.util.GlideApp
import com.greenfriends.zeroway.util.POST_ID
import com.greenfriends.zeroway.util.binding.BindingFragment

class CommunityPostDetailFragment :
    BindingFragment<FragmentCommunityPostDetailBinding>(R.layout.fragment_community_post_detail) {

    private val viewModel: CommunityPostDetailViewModel by viewModels { ViewModelFactory() }
    private lateinit var communityPostDetailAdapter: CommunityPostDetailAdapter
    private lateinit var communityPostDetailCommentsAdapter: CommunityPostDetailCommentsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner

        setPostId()
        setUserProfileImg()
        setObservers()
        setOnClickListener()
        setCommunityPostDetailAdapter()
        setNavigation()
        getPostDetail()
    }

    private fun setPostId() {
        arguments?.getString(POST_ID)?.let { viewModel.setPostId(it) }
    }

    private fun setUserProfileImg() {
        if (!getProfileImgUrl().isNullOrEmpty()) {
            GlideApp.with(this)
                .load(getProfileImgUrl())
                .into(binding.communityPostDetailCommentProfileIv)
        }
    }

    private fun setObservers() {
        viewModel.communityPostDetailResponse.observe(
            viewLifecycleOwner
        ) { communityPostDetailResponse ->
            communityPostDetailAdapter.submitList(listOf(communityPostDetailResponse))
            communityPostDetailCommentsAdapter.submitList(listOf(communityPostDetailResponse))
        }

        viewModel.commentRegisterEvent.observe(
            viewLifecycleOwner, EventObserver {
                viewModel.setPostComment(getJwt()!!, viewModel.getPostId()!!, it)
            }
        )

        viewModel.communityPostDetailDeleteEvent.observe(
            viewLifecycleOwner, EventObserver { isDeleted ->
                if (isDeleted) {
                    Toast.makeText(requireContext(), "게시물이 삭제되었습니다.", Toast.LENGTH_SHORT).show()
                    startCommunityFragment()
                } else {
                    Toast.makeText(requireContext(), "해당 게시물 삭제 권한이 없습니다.", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        )

        viewModel.communityPostDetailCommentDeleteEvent.observe(
            viewLifecycleOwner, EventObserver { isDeleted ->
                if (isDeleted) {
                    Toast.makeText(requireContext(), "댓글이 삭제되었습니다.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "해당 댓글 삭제 권한이 없습니다.", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        )

        viewModel.communityPostDetailReportEvent.observe(
            viewLifecycleOwner, EventObserver { isSuccess ->
                if (isSuccess) {
                    Toast.makeText(requireContext(), "게시물이 신고되었습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        )

        viewModel.communityPostDetailCommentReportEvent.observe(
            viewLifecycleOwner, EventObserver { isSuccess ->
                if (isSuccess) {
                    Toast.makeText(requireContext(), "댓글이 신고되었습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }

    private fun setOnClickListener() {
        with(binding) {
            communityPostDetailCommentRegisterTv.setOnClickListener {
                if (!communityPostDetailCommentEt.text.isNullOrEmpty()) {
                    viewModel.setCommentRegisterEvent(communityPostDetailCommentEt.text.toString())
                    communityPostDetailCommentEt.text.clear()
                }
            }
        }
    }

    private fun setCommunityPostDetailAdapter() {
        communityPostDetailAdapter = CommunityPostDetailAdapter(requireContext())
        communityPostDetailCommentsAdapter = CommunityPostDetailCommentsAdapter()
        binding.communityPostDetailRv.adapter =
            ConcatAdapter(communityPostDetailAdapter, communityPostDetailCommentsAdapter)

        communityPostDetailAdapter.setOnCommunityPostDetailPostClickListener(object :
            OnCommunityPostDetailPostClickListener {

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

            override fun deleteCommunityPost(communityPostDetailResponse: CommunityPostDetailResponse) {
                val confirmDialog = ConfirmDialogFragment("게시물을 삭제하시겠습니까?", "삭제된 게시물은 복구할 수 없습니다.")
                confirmDialog.setOnConfirmDialogClickListener(object :
                    OnConfirmDialogClickListener {

                    override fun onSuccess(isSuccess: Boolean) {
                        if (isSuccess) {
                            viewModel.deletePost(
                                getJwt()!!,
                                communityPostDetailResponse.postId.toString()
                            )
                        }
                    }
                })
                confirmDialog.show(parentFragmentManager, "ConfirmDialog")
            }

            override fun reportCommunityPost(communityPostDetailResponse: CommunityPostDetailResponse) {
                val reportDialog = ReportDialogFragment()
                reportDialog.setOnReportDialogClickListener(object :
                    OnReportDialogClickListener {

                    override fun onSuccess(isSuccess: Boolean, option: String?) {
                        if (isSuccess) {
                            val communityReportRequest =
                                CommunityReportRequest(communityPostDetailResponse.postId, option!!)
                            viewModel.reportPost(getJwt()!!, communityReportRequest)
                        }
                    }
                })
                reportDialog.show(parentFragmentManager, "ReportDialog")
            }
        })

        communityPostDetailCommentsAdapter.setOnCommunityPostDetailCommentClickListener(object :
            OnCommunityPostDetailCommentClickListener {

            override fun setCommunityPostCommentLike(communityPostDetailComment: CommunityPostDetailComment) {
                viewModel.setPostCommentLike(
                    getJwt()!!,
                    communityPostDetailComment.commentId.toString(),
                    communityPostDetailComment.liked
                )
            }

            override fun deleteCommunityPostComment(communityPostDetailComment: CommunityPostDetailComment) {
                val confirmDialog = ConfirmDialogFragment("댓글을 삭제하시겠습니까?", "삭제된 댓글은 복구할 수 없습니다.")
                confirmDialog.setOnConfirmDialogClickListener(object :
                    OnConfirmDialogClickListener {

                    override fun onSuccess(isSuccess: Boolean) {
                        if (isSuccess) {
                            viewModel.deletePostComment(
                                getJwt()!!,
                                communityPostDetailComment.commentId.toString()
                            )
                        }
                    }
                })
                confirmDialog.show(parentFragmentManager, "ConfirmDialog")
            }

            override fun reportCommunityPostComment(communityPostDetailComment: CommunityPostDetailComment) {
                val reportDialog = ReportDialogFragment()
                reportDialog.setOnReportDialogClickListener(object :
                    OnReportDialogClickListener {

                    override fun onSuccess(isSuccess: Boolean, option: String?) {
                        if (isSuccess) {
                            val communityReportRequest =
                                CommunityReportRequest(
                                    communityPostDetailComment.commentId,
                                    option!!
                                )
                            viewModel.reportPostComment(getJwt()!!, communityReportRequest)
                        }
                    }
                })
                reportDialog.show(parentFragmentManager, "ReportDialog")
            }
        })
    }

    private fun setNavigation() {
        binding.communityPostDetailTb.setNavigationOnClickListener {
            startCommunityFragment()
        }
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