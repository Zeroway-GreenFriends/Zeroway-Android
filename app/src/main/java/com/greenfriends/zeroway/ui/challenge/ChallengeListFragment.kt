package com.greenfriends.zeroway.ui.challenge

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.greenfriends.zeroway.R
import com.greenfriends.zeroway.databinding.FragmentChallengeListBinding
import com.greenfriends.zeroway.model.ChallengeLevelResponse
import com.greenfriends.zeroway.model.ChallengeListResponse
import com.greenfriends.zeroway.model.ShopList
import com.greenfriends.zeroway.model.UseList
import com.greenfriends.zeroway.network.ChallengeListView
import com.greenfriends.zeroway.network.ChallengeService
import com.greenfriends.zeroway.network.ChallengeUpdateView
import com.greenfriends.zeroway.ui.ShopAdapter

class ChallengeListFragment : Fragment(),ChallengeListView, ChallengeUpdateView {
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

        challengeList.clear()

        for (i in result){
            challengeList.add(i)
        }

        val challengeListAdapter = ChallengeListAdapter(challengeList)
        binding.challengeListRv.adapter = challengeListAdapter
        binding.challengeListRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        challengeListAdapter.setMyItemClickListener(object: ChallengeListAdapter.MyItemClickListener{
            override fun onItemClick(challengeList: ChallengeListResponse) {

                //patch 수행
                updateChallenge(getJwt()!!,challengeList.challengeId)

            }
        })
    }

    override fun onChallengeListFailure() {
        TODO("Not yet implemented")
    }

    private fun getJwt(): String? {
        val sharedPreferences =
            activity?.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return sharedPreferences!!.getString("jwt", null)
    }

    private fun updateChallenge(token: String, challengeId: Long) {
        val challengeService = ChallengeService()
        challengeService.setChallengeUpdateView(this)
        challengeService.updateChallenge(token,challengeId)
    }

    override fun onChallengeUpdateSuccess(result: ChallengeLevelResponse) {

        getChallengeList(getJwt()!!)

        when(result.level){
            //레벨업 했을 경우에 activity 띄우기
        }
    }

    override fun onChallengeUpdateFailure() {
        TODO("Not yet implemented")
    }

}