package com.greenfriends.zeroway.ui.community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.greenfriends.zeroway.R
import com.greenfriends.zeroway.databinding.FragmentCommunityBinding

class CommunityFragment : Fragment() {
    private lateinit var binding: FragmentCommunityBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCommunityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerCommunityPost()
    }

    private fun registerCommunityPost() {
        binding.communityFab.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_fl, CommunityPostRegisterFragment())
                .commit()
        }
    }
}