package com.greenfriends.zeroway.presentation.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.greenfriends.zeroway.databinding.FragmentMyPageBinding

class MyPageFragment : Fragment() {
    private lateinit var binding: FragmentMyPageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyPageBinding.inflate(inflater, container, false)

//        binding.mypageNoticeTv.setOnClickListener {
//            activity?.supportFragmentManager?.beginTransaction()
//                ?.replace(R.id.main_fl, NoticeFragment())
//                ?.commitAllowingStateLoss()
//
//        }
//        binding.mypageAlarmTv.setOnClickListener {
//            activity?.supportFragmentManager?.beginTransaction()
//                ?.replace(R.id.main_fl, MyPageSettingAlarmFragment())
//                ?.commitAllowingStateLoss()
//        }
        return binding.root
    }
}