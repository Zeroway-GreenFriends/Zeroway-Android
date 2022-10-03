package com.greenfriends.zeroway.ui.store.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.greenfriends.zeroway.R
import com.greenfriends.zeroway.STORE_ID
import com.greenfriends.zeroway.databinding.FragmentStorePostDetailBinding
import com.greenfriends.zeroway.ui.common.ViewModelFactory
import com.greenfriends.zeroway.ui.store.viewmodel.StorePostDetailViewModel

class StorePostDetailFragment : Fragment() {

    private val viewModel: StorePostDetailViewModel by viewModels { ViewModelFactory() }
    private lateinit var binding: FragmentStorePostDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStorePostDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        setStoreId()
        setObserve()
        setNavigation()
    }

    private fun setStoreId() {
        arguments?.getString(STORE_ID)?.let { viewModel.setStoreId(it) }
    }

    private fun setObserve() {
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