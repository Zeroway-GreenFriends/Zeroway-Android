package com.greenfriends.zeroway.presentation.mypage.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.greenfriends.zeroway.databinding.FragmentMyPagePostBinding

class MyPagePostFragment : Fragment() {
    private lateinit var binding: FragmentMyPagePostBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyPagePostBinding.inflate(inflater, container, false)


        return binding.root
    }
}