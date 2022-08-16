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
import com.greenfriends.zeroway.ui.common.ViewModelFactory

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
    }

    private fun setCommunityAdapter() {
        adapter = CommunityAdapter()
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
}