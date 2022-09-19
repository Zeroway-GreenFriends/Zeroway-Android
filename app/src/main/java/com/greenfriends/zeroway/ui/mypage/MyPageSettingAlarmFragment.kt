package com.greenfriends.zeroway.ui.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.greenfriends.zeroway.databinding.FragmentMyPageSettingAlarmBinding
import com.greenfriends.zeroway.databinding.FragmentNoticeBinding

class MyPageSettingAlarmFragment : Fragment() {
    private lateinit var binding: FragmentMyPageSettingAlarmBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyPageSettingAlarmBinding.inflate(inflater, container, false)

        return binding.root
    }
}