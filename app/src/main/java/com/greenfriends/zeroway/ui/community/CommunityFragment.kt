package com.greenfriends.zeroway.ui.community

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.greenfriends.zeroway.R
import com.greenfriends.zeroway.databinding.FragmentCommunityBinding
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
        Log.d("JWT", getJwt().toString())
        viewModel.getPosts(getJwt()!!, viewModel.getSort()!!)
        setCommunityAdapter()
        setObserve()
        startCommunityPostRegisterFragment()
    }

    private fun setObserve() {
        viewModel.sort.observe(
            viewLifecycleOwner
        ) {
            binding.sort = it
        }

        viewModel.communityPosts.observe(
            viewLifecycleOwner
        ) {
            adapter.submitList(it)
        }

        viewModel.communityPostDetailEvent.observe(
            viewLifecycleOwner, EventObserve {
                startCommunityPostDetailFragment(it.toString())
            }
        )
    }

    private fun setCommunityAdapter() {
        adapter = CommunityAdapter(viewModel)
        binding.communityPostRv.adapter = adapter
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