package com.greenfriends.zeroway.ui.community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.greenfriends.zeroway.R
import com.greenfriends.zeroway.databinding.FragmentCommunityBinding
import com.greenfriends.zeroway.model.CommunityPost
import com.greenfriends.zeroway.ui.common.EventObserve
import com.greenfriends.zeroway.ui.common.ViewModelFactory
import com.greenfriends.zeroway.ui.communitypostdetail.CommunityPostDetailFragment
import com.greenfriends.zeroway.ui.communitypostregister.CommunityPostRegisterFragment

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

        getPosts()
        setObserve()
        setOnClickListener()
        setCommunityAdapter()
        startCommunityPostRegisterFragment()
    }

    private fun getPosts() {
        viewModel.getPosts(getJwt()!!, viewModel.getSort()!!)
    }

    private fun setObserve() {
        viewModel.sort.observe(
            viewLifecycleOwner
        ) { sort ->
            binding.sort = sort
        }

        viewModel.communityPosts.observe(
            viewLifecycleOwner
        ) { communityPosts ->
            adapter.submitList(communityPosts)
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
                viewModel.setSort("createdAt")
                getPosts()
            }
            communityPopularityTv.setOnClickListener {
                viewModel.setSort("like")
                getPosts()
            }
        }
    }

    private fun setCommunityAdapter() {
        adapter = CommunityAdapter(viewModel)
        binding.communityPostRv.adapter = adapter
        adapter.setOnCommunityItemClickListener(object : OnCommunityItemClickListener {

            override fun deleteCommunityPost() {
                TODO("Not yet implemented")
            }

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
        bundle.putString("postId", postId)

        val communityPostDetailFragment = CommunityPostDetailFragment()
        communityPostDetailFragment.arguments = bundle
        parentFragmentManager.beginTransaction()
            .replace(R.id.main_fl, communityPostDetailFragment)
            .commit()
    }
}