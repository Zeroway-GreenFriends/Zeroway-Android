package com.greenfriends.zeroway.ui.challenge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.greenfriends.zeroway.R
import com.greenfriends.zeroway.databinding.FragmentChallengeListBinding

class ChallengeListFragment : Fragment() {
    private lateinit var binding: FragmentChallengeListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChallengeListBinding.inflate(inflater, container, false)

        binding.challengeListCheckBtn1.setOnClickListener{
            binding.challengeListCheckBtn1.setImageResource(R.drawable.ic_check_circle_mint)
        }
        binding.challengeListCheckBtn2.setOnClickListener{
            binding.challengeListCheckBtn2.setImageResource(R.drawable.ic_check_circle_mint)
        }
        binding.challengeListCheckBtn3.setOnClickListener{
            binding.challengeListCheckBtn3.setImageResource(R.drawable.ic_check_circle_mint)
        }
        binding.challengeListCheckBtn4.setOnClickListener{
            binding.challengeListCheckBtn4.setImageResource(R.drawable.ic_check_circle_mint)
        }
        binding.challengeListCheckBtn5.setOnClickListener{
            binding.challengeListCheckBtn5.setImageResource(R.drawable.ic_check_circle_mint)
        }

        return binding.root
    }
}