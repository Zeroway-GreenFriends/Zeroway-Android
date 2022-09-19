package com.greenfriends.zeroway.ui.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.greenfriends.zeroway.databinding.FragmentNoticeBinding

class NoticeFragment : Fragment() {
    private lateinit var binding: FragmentNoticeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoticeBinding.inflate(inflater, container, false)

        return binding.root
    }
}