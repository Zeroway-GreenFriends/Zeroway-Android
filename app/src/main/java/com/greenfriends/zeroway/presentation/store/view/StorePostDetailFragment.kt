package com.greenfriends.zeroway.presentation.store.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.greenfriends.zeroway.R
import com.greenfriends.zeroway.databinding.FragmentStorePostDetailBinding
import com.greenfriends.zeroway.presentation.common.ViewModelFactory
import com.greenfriends.zeroway.presentation.store.viewmodel.StorePostDetailViewModel
import com.greenfriends.zeroway.util.STORE_ID
import com.greenfriends.zeroway.util.binding.BindingFragment

class StorePostDetailFragment :
    BindingFragment<FragmentStorePostDetailBinding>(R.layout.fragment_store_post_detail) {

    private val viewModel: StorePostDetailViewModel by viewModels { ViewModelFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner

        setStoreId()
        setObservers()
        setNavigation()
    }

    private fun setStoreId() {
        arguments?.getString(STORE_ID)?.let { viewModel.setStoreId(it) }
    }

    private fun setObservers() {
        viewModel.storeId.observe(
            viewLifecycleOwner
        ) { storeId ->
            viewModel.getStoreDetail(storeId)
        }

        viewModel.storePostDetailResponse.observe(
            viewLifecycleOwner
        ) { storePostDetailResponse ->
            binding.storePostDetail = storePostDetailResponse
        }
    }

    private fun setNavigation() {
        binding.storePostDetailTb.setNavigationOnClickListener {
            startStoreFragment()
        }
    }

    private fun startStoreFragment() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.main_fl, StoreFragment())
            .commit()
    }
}