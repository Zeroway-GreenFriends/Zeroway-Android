package com.greenfriends.zeroway.presentation.community.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.greenfriends.zeroway.R
import com.greenfriends.zeroway.data.model.CommunityPost
import com.greenfriends.zeroway.data.model.CommunityReportRequest
import com.greenfriends.zeroway.databinding.FragmentCommunityBinding
import com.greenfriends.zeroway.presentation.common.ViewModelFactory
import com.greenfriends.zeroway.presentation.community.OnCommunityItemClickListener
import com.greenfriends.zeroway.presentation.community.OnReportDialogClickListener
import com.greenfriends.zeroway.presentation.community.adapter.CommunityAdapter
import com.greenfriends.zeroway.presentation.community.viewmodel.CommunityViewModel
import com.greenfriends.zeroway.util.EventObserver
import com.greenfriends.zeroway.util.POST_ID
import com.greenfriends.zeroway.util.binding.BindingFragment

class CommunityFragment : BindingFragment<FragmentCommunityBinding>(R.layout.fragment_community) {

    private val viewModel: CommunityViewModel by viewModels { ViewModelFactory() }
    private lateinit var adapter: CommunityAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner

        getPosts(isInit = true, state = getState())
        setObservers()
        setOnClickListener()
        setCommunityAdapter()
        setOnScrollChangeListener()
        startCommunityPostRegisterFragment()
    }

    private fun getPosts(page: Long = 1, size: Long = 5, isInit: Boolean = false, state: String) {
        when (isInit) {
            true -> {
                viewModel.getPosts(
                    getJwt()!!,
                    viewModel.getSort()!!,
                    page,
                    size,
                    viewModel.getChallenge(),
                    viewModel.getReview()
                )
            }
            else -> {
                adapter.setLoading(true)
                val handler = Handler(Looper.getMainLooper())
                handler.postDelayed(
                    {
                        if (state == getState()) {
                            adapter.setLoading(false)
                            viewModel.getPosts(
                                getJwt()!!,
                                viewModel.getSort()!!,
                                page,
                                size,
                                viewModel.getChallenge(),
                                viewModel.getReview()
                            )
                        }
                    }, 1000
                )
            }
        }
    }

    private fun getState(): String {
        return viewModel.getSort().toString() + viewModel.getReview()
            .toString() + viewModel.getChallenge().toString()
    }

    private fun setObservers() {
        viewModel.sort.observe(
            viewLifecycleOwner
        ) { sort ->
            binding.sort = sort
        }

        viewModel.review.observe(
            viewLifecycleOwner
        ) { review ->
            binding.review = review
        }

        viewModel.challenge.observe(
            viewLifecycleOwner
        ) { challenge ->
            binding.challenge = challenge
        }

        viewModel.communityPostDetailEvent.observe(
            viewLifecycleOwner, EventObserver { postId ->
                startCommunityPostDetailFragment(postId.toString())
            }
        )

        viewModel.communityPostReportEvent.observe(
            viewLifecycleOwner, EventObserver { isSuccess ->
                if (isSuccess) {
                    Toast.makeText(requireContext(), "게시물이 신고되었습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }

    private fun setOnClickListener() {
        with(binding) {
            communityLatestTv.setOnClickListener {
                adapter.clear()
                viewModel.setIsLoading(true)
                viewModel.setSort("createdAt")
                viewModel.setPage(1)
                getPosts(isInit = true, state = getState())
            }

            communityPopularityTv.setOnClickListener {
                adapter.clear()
                viewModel.setIsLoading(true)
                viewModel.setSort("like")
                viewModel.setPage(1)
                getPosts(isInit = true, state = getState())
            }

            communityReviewTv.setOnClickListener {
                adapter.clear()
                viewModel.setIsLoading(true)
                viewModel.setReview()
                viewModel.setPage(1)
                getPosts(isInit = true, state = getState())
            }

            communityChallengeTv.setOnClickListener {
                adapter.clear()
                viewModel.setIsLoading(true)
                viewModel.setChallenge()
                viewModel.setPage(1)
                getPosts(isInit = true, state = getState())
            }
        }
    }

    private fun setCommunityAdapter() {
        adapter = CommunityAdapter(viewModel, requireContext())
        binding.communityPostRv.adapter = adapter
        adapter.setOnCommunityItemClickListener(object : OnCommunityItemClickListener {

            override fun setCommunityPostLike(communityPost: CommunityPost) {
                viewModel.setPostLike(
                    getJwt()!!,
                    communityPost.postId.toString(),
                    communityPost.liked
                )
            }

            override fun setCommunityPostBookmark(communityPost: CommunityPost) {
                viewModel.setPostBookmark(
                    getJwt()!!,
                    communityPost.postId.toString(),
                    communityPost.bookmarked
                )
            }

            override fun reportCommunityPost(communityPost: CommunityPost) {
                val reportDialog = ReportDialogFragment()
                reportDialog.setOnReportDialogClickListener(object :
                    OnReportDialogClickListener {

                    override fun onSuccess(isSuccess: Boolean, option: String?) {
                        if (isSuccess) {
                            val communityReportRequest =
                                CommunityReportRequest(communityPost.postId, option!!)
                            viewModel.reportPost(getJwt()!!, communityReportRequest)
                        }
                    }
                })
                reportDialog.show(parentFragmentManager, "ReportDialog")
            }
        })

        viewModel.communityPosts.observe(
            viewLifecycleOwner
        ) { communityPosts ->
            if (!communityPosts.isNullOrEmpty()) {
                adapter.submitCommunityPosts(communityPosts)
                viewModel.setIsLoading(false)
            }
        }
    }

    private fun setOnScrollChangeListener() {
        binding.communityPostRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val totalItemCount = recyclerView.adapter!!.itemCount
                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                if (!recyclerView.canScrollVertically(1) && lastVisibleItemPosition == totalItemCount - 1 && !viewModel.getIsLoading()!!) {
                    viewModel.setIsLoading(true)
                    viewModel.setPage(viewModel.getPage()!! + 1)
                    getPosts(page = viewModel.getPage()!!, state = getState())
                }
            }
        })
    }

    private fun getJwt(): String? {
        val sharedPreferences =
            activity?.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return sharedPreferences!!.getString("jwt", null)
    }

    private fun startCommunityPostRegisterFragment() {
        binding.communityFab.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_fl, CommunityPostRegisterFragment())
                .commit()
        }
    }

    private fun startCommunityPostDetailFragment(postId: String) {
        val bundle = Bundle()
        bundle.putString(POST_ID, postId)

        val communityPostDetailFragment = CommunityPostDetailFragment()
        communityPostDetailFragment.arguments = bundle
        parentFragmentManager.beginTransaction()
            .replace(R.id.main_fl, communityPostDetailFragment)
            .commit()
    }
}