package com.greenfriends.zeroway.ui.challenge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.greenfriends.zeroway.R
import com.greenfriends.zeroway.databinding.FragmentChallengeListBinding
import com.greenfriends.zeroway.model.ChallengeListResponse
import com.greenfriends.zeroway.model.ShopList
import com.greenfriends.zeroway.model.UseList
import com.greenfriends.zeroway.network.ChallengeListView
import com.greenfriends.zeroway.network.ChallengeService
import com.greenfriends.zeroway.ui.ShopAdapter

class ChallengeListFragment : Fragment(),ChallengeListView {
    private lateinit var binding: FragmentChallengeListBinding

    private var challengeList = ArrayList<ChallengeListResponse>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChallengeListBinding.inflate(inflater, container, false)

        getChallengeList(getJwt()!!)

        return binding.root
    }

    private fun getChallengeList(token: String) {
        val challengeListService = ChallengeService()
        challengeListService.setChallengeListView(this)
        challengeListService.getChallengeList(token)
    }

    override fun onChallengeListSuccess(result: List<ChallengeListResponse>) {
        for (i in result){
            challengeList.add(i)
        }

        val challengeListAdapter = ChallengeListAdapter(challengeList)
        binding.challengeListRv.adapter = challengeListAdapter
        binding.challengeListRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

    }

    override fun onChallengeListFailure() {
        TODO("Not yet implemented")
    }

    private fun getJwt(): String? {
        val sharedPreferences =
            activity?.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return sharedPreferences!!.getString("jwt", null)
    }

}