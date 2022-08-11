package com.greenfriends.zeroway.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.greenfriends.zeroway.databinding.FragmentChallengeCharacterBinding

class ChallengeCharacterFragment : Fragment() {
    private lateinit var binding: FragmentChallengeCharacterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChallengeCharacterBinding.inflate(inflater, container, false)
        return binding.root
    }
}