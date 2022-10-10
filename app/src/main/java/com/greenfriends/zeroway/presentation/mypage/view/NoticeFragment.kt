package com.greenfriends.zeroway.presentation.mypage.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.greenfriends.zeroway.R
import com.greenfriends.zeroway.data.model.NoticeResponse
import com.greenfriends.zeroway.databinding.FragmentNoticeBinding
import com.greenfriends.zeroway.presentation.common.ViewModelFactory
import com.greenfriends.zeroway.presentation.mypage.adapter.NoticeAdapter
import com.greenfriends.zeroway.presentation.mypage.viewmodel.NoticeViewModel

class NoticeFragment : Fragment() {

    private val viewModel: NoticeViewModel by activityViewModels { ViewModelFactory() }

    private lateinit var binding: FragmentNoticeBinding
    private lateinit var noticeAdapter: NoticeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoticeBinding.inflate(inflater, container, false)


        getNotice()
        setNoticeAdapter()
        setObserve()

        return binding.root
    }

    private fun setObserve() {
        viewModel.noticeResponse.observe(
            viewLifecycleOwner
        ) { nr ->
            noticeAdapter.submitList(nr)
        }

    }

    private fun setNoticeAdapter() {
        noticeAdapter = NoticeAdapter(viewModel)
        noticeAdapter.setMyItemClickListener(object : NoticeAdapter.MyItemClickListener {
            override fun onItemClick(notice: NoticeResponse) {

                viewModel.setAnnounceId(notice.announceId)

                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.main_fl, NoticeDetailFragment())
                    ?.commitAllowingStateLoss()

            }

        })
        binding.alarmRv.adapter = noticeAdapter
    }

    private fun getNotice() {
        viewModel.getNotice()
    }

}