package com.greenfriends.zeroway.ui.community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.greenfriends.zeroway.databinding.FragmentCommunityPostRegisterBinding
import com.greenfriends.zeroway.ui.common.ViewModelFactory

class CommunityPostRegisterFragment : Fragment() {

    private val viewModel: CommunityPostRegisterViewModel by viewModels { ViewModelFactory() }
    private lateinit var binding: FragmentCommunityPostRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCommunityPostRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner

        setObserve()
        setChallenge()
    }

    private fun setObserve() {
        viewModel.isChallenge.observe(
            viewLifecycleOwner
        ) {
            binding.isChallenge = it
        }
    }

    private fun setChallenge() {
        binding.communityPostRegisterChallengeTv.setOnClickListener {
            viewModel.setIsChallenge()
        }
    }
}