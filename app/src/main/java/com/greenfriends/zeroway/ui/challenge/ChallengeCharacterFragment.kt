package com.greenfriends.zeroway.ui.challenge

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.greenfriends.zeroway.R
import com.greenfriends.zeroway.databinding.FragmentChallengeCharacterBinding
import com.greenfriends.zeroway.ui.common.ViewModelFactory

class ChallengeCharacterFragment : Fragment() {

    private val viewModel: ChallengeViewModel by viewModels { ViewModelFactory() }
    private lateinit var binding: FragmentChallengeCharacterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChallengeCharacterBinding.inflate(inflater, container, false)

        binding.challengeCharacterChallengeBtn.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.main_fl, ChallengeListFragment())
                ?.commitAllowingStateLoss()
        }

        getChallenge()
        setObserve()

        return binding.root
    }

    private fun setObserve() {
        viewModel.cr.observe(
            viewLifecycleOwner
        ) { cr ->
            binding.challengeResponse = cr
            Glide.with(binding.challengeCharacterLevelImg.context)
                .load(Uri.parse(cr.imgUrl))
                .disallowHardwareConfig()
                .into(binding.challengeCharacterLevelImg)
            binding.challengeCharacterLevelPb.setProgress(cr.exp)
            saveLevel(cr.level)
        }
    }

    private fun getChallenge() {
        viewModel.getUserChallenge(getJwt()!!)
    }

    private fun saveLevel(level: Int) {
        val sharedPreferences = activity?.getSharedPreferences("level", AppCompatActivity.MODE_PRIVATE)
        val editor = sharedPreferences!!.edit()
        editor.putString("level", level.toString())
        editor.apply()
    }

    private fun getJwt(): String? {
        val sharedPreferences =
            activity?.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return sharedPreferences!!.getString("jwt", null)
    }

}