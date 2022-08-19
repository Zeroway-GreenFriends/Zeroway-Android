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
import com.greenfriends.zeroway.ui.common.ViewModelFactory

class CommunityPostDetailFragment : Fragment() {

    private val viewModel: CommunityPostDetailViewModel by viewModels { ViewModelFactory() }
    private lateinit var binding: FragmentCommunityPostDetailBinding

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
        arguments?.getString("postId")?.let { viewModel.getPostDetail(getJwt()!!, it) }

        setObserve()
    }

    private fun setObserve() {
        val communityPostDetailAdapter = CommunityPostDetailAdapter()
        val communityPostDetailCommentsAdapter = CommunityPostDetailCommentsAdapter()
        binding.communityPostDetailRv.adapter =
            ConcatAdapter(communityPostDetailAdapter, communityPostDetailCommentsAdapter)

        viewModel.communityPostDetailResponse.observe(
            viewLifecycleOwner
        ) { communityPostDetailResponse ->
            communityPostDetailAdapter.submitList(listOf(communityPostDetailResponse))
            communityPostDetailCommentsAdapter.submitList(listOf(communityPostDetailResponse))
        }
    }

    private fun getJwt(): String? {
        val sharedPreferences =
            activity?.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return sharedPreferences!!.getString("jwt", null)
    }
}