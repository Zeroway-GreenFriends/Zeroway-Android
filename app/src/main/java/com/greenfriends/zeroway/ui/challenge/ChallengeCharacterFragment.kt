package com.greenfriends.zeroway.ui.challenge

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.greenfriends.zeroway.R
import com.greenfriends.zeroway.databinding.FragmentChallengeCharacterBinding
import com.greenfriends.zeroway.model.ChallengeResponse
import com.greenfriends.zeroway.network.ChallengeService
import com.greenfriends.zeroway.network.ChallengeView
import com.greenfriends.zeroway.network.HomeService
import com.greenfriends.zeroway.ui.alarm.AlarmFragment

class ChallengeCharacterFragment : Fragment(), ChallengeView {
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

        getChallenge(getJwt()!!)


        return binding.root
    }

    private fun getChallenge(token: String) {
        val challengeService = ChallengeService()
        challengeService.setChallengeView(this)
        challengeService.getChallenge(token)
    }

    override fun onChallengeSuccess(result: ChallengeResponse) {
        Log.e("challenge", result.toString())

        saveLevel(result.level)

        binding.challengeCharacterNicknameTv.text = result.nickname
        binding.challengeCharacterLevelTv.text = "Lv." + result.level.toString()
        binding.challengeCharacterLevelPb.setProgress(result.exp)

        Glide.with(binding.challengeCharacterLevelImg.context)
            .load(result.imgUrl)
            .into(binding.challengeCharacterLevelImg)
    }

    override fun onChallengeFailure() {
        TODO("Not yet implemented")
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