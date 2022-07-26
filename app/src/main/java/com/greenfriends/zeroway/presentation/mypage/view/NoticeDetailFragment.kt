package com.greenfriends.zeroway.presentation.mypage.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.greenfriends.zeroway.R
import com.greenfriends.zeroway.databinding.FragmentNoticeDetailBinding
import com.greenfriends.zeroway.presentation.common.ViewModelFactory
import com.greenfriends.zeroway.presentation.mypage.viewmodel.MyPageViewModel

class NoticeDetailFragment : Fragment() {

    private val viewModel: MyPageViewModel by activityViewModels { ViewModelFactory() }

    private lateinit var binding: FragmentNoticeDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoticeDetailBinding.inflate(inflater, container, false)

        setObserve()
        getNoticeDetail(viewModel.getAnnounceId()!!)
        setNavigation()

        return binding.root
    }

    private fun setObserve() {
        viewModel.noticeDetailResponse.observe(
            viewLifecycleOwner
        ) {
            binding.noticeDetailResponse = it
        }
    }

    private fun getNoticeDetail(announceId : Long) {
        viewModel.getNoticeDetail(announceId)
    }

    private fun setNavigation() {
        binding.noticeDetailTb.setNavigationOnClickListener {
            startNoticeFragment()
        }
    }

    private fun startNoticeFragment() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.main_fl, NoticeFragment())
            .commit()
    }

}