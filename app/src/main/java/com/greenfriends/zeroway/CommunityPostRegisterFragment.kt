package com.greenfriends.zeroway

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.greenfriends.zeroway.databinding.FragmentCommunityPostRegisterBinding

class CommunityPostRegisterFragment: Fragment() {
    private lateinit var binding: FragmentCommunityPostRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCommunityPostRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }
}