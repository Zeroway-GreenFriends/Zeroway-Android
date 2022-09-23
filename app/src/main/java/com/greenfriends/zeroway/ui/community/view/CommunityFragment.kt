package com.greenfriends.zeroway.ui.community.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.greenfriends.zeroway.POST_ID
import com.greenfriends.zeroway.R
import com.greenfriends.zeroway.data.model.CommunityPost
import com.greenfriends.zeroway.databinding.FragmentCommunityBinding
import com.greenfriends.zeroway.ui.common.EventObserve
import com.greenfriends.zeroway.ui.common.ViewModelFactory
import com.greenfriends.zeroway.ui.community.OnCommunityItemClickListener
import com.greenfriends.zeroway.ui.community.adapter.CommunityAdapter
import com.greenfriends.zeroway.ui.community.viewmodel.CommunityViewModel

class CommunityFragment : Fragment() {

    private val viewModel: CommunityViewModel by viewModels { ViewModelFactory() }
    private lateinit var binding: FragmentCommunityBinding
    private lateinit var adapter: CommunityAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCommunityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner

        getPosts(isInit = true, state = getState())
        setObserve()
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

    private fun setObserve() {
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
            viewLifecycleOwner, EventObserve { postId ->
                startCommunityPostDetailFragment(postId.toString())
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