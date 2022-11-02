package com.greenfriends.zeroway.presentation.challenge.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.greenfriends.zeroway.R
import com.greenfriends.zeroway.data.model.ChallengeListResponse
import com.greenfriends.zeroway.databinding.FragmentChallengeListBinding
import com.greenfriends.zeroway.presentation.challenge.adapter.ChallengeListAdapter
import com.greenfriends.zeroway.presentation.challenge.viewmodel.ChallengeViewModel
import com.greenfriends.zeroway.util.EventObserver
import com.greenfriends.zeroway.presentation.common.ViewModelFactory

class ChallengeListFragment : Fragment() {

    private val viewModel: ChallengeViewModel by viewModels { ViewModelFactory() }
    private lateinit var challengeListAdapter: ChallengeListAdapter

    private lateinit var binding: FragmentChallengeListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChallengeListBinding.inflate(inflater, container, false)

        getChallengeList()
        setObserve()
        setChallengeListAdapter()

        binding.challengeListOkBtn.setOnClickListener {
            Log.e("level", getLevel().toString())
            Log.e("patch level", getPatchLevel().toString())
            if ((getLevel()?.toInt()?.plus(1)) == getPatchLevel()?.toInt()) {
                saveLevel(getPatchLevel()?.toInt()!!)
                startActivity(Intent(context, LevelUpActivity::class.java))

            } else {
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.main_fl, ChallengeCharacterFragment())
                    ?.commitAllowingStateLoss()
            }
        }

        return binding.root
    }

    private fun setObserve() {

        viewModel.challengeList.observe(
            viewLifecycleOwner
        ) { challengeList ->

            Log.e("c-L", challengeList.toString())

            challengeListAdapter.submitList(challengeList)
        }

        viewModel.challengeLevelResponse.observe(
            viewLifecycleOwner
        ) { challengeLevelResponse ->
            savePatchLevel(challengeLevelResponse.level)
        }

        viewModel.updateEvent.observe(
            viewLifecycleOwner,
            EventObserver {
                getChallengeList()
            }
        )

    }

    private fun getChallengeList() {
        viewModel.getChallengeList(getJwt()!!)
    }

    private fun updateChallengeList(challengeId: Long) {
        viewModel.updateChallenge(getJwt()!!, challengeId)
        //getChallengeList()
    }

    private fun setChallengeListAdapter() {
        challengeListAdapter = ChallengeListAdapter(viewModel)
        challengeListAdapter.setMyItemClickListener(object :
            ChallengeListAdapter.MyItemClickListener {
            override fun onItemClick(challengeList: ChallengeListResponse) {
                //viewModel.updateChallenge(getJwt()!!, challengeList.challengeId)
                Log.e("challengeId", challengeList.challengeId.toString())
                updateChallengeList(challengeList.challengeId)
                //getChallengeList()
            }
        })
        binding.challengeListRv.adapter = challengeListAdapter
    }


//    private fun getChallengeList(token: String) {
//        val challengeListService = ChallengeService()
//        challengeListService.setChallengeListView(this)
//        challengeListService.getChallengeList(token)
//    }

//    override fun onChallengeListSuccess(result: List<ChallengeListResponse>) {
//
//        challengeList.clear()
//
//        for (i in result) {
//            challengeList.add(i)
//        }
//
//        Log.e("challengeList",challengeList.toString())
//
//        val challengeListAdapter = ChallengeListAdapter(challengeList)
//        binding.challengeListRv.adapter = challengeListAdapter
//        binding.challengeListRv.layoutManager =
//            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//
//        challengeListAdapter.setMyItemClickListener(object :
//            ChallengeListAdapter.MyItemClickListener {
//            override fun onItemClick(challengeList: ChallengeListResponse) {
//
//                updateChallenge(getJwt()!!, challengeList.challengeId)
//
//            }
//        })
//
//        //savePatchLevel(1)
//
//        binding.challengeListOkBtn.setOnClickListener {
//            Log.e("level",getLevel().toString())
//            Log.e("patch level",getPatchLevel().toString())
//            if ((getLevel()?.toInt()?.plus(1)) == getPatchLevel()?.toInt()) {
//                saveLevel(getPatchLevel()?.toInt()!!)
//                startActivity(Intent(context, LevelUpActivity::class.java))
//
//            } else {
//                activity?.supportFragmentManager?.beginTransaction()
//                    ?.replace(R.id.main_fl, ChallengeCharacterFragment())
//                    ?.commitAllowingStateLoss()
//            }
//        }
//
//    }
//
//    override fun onChallengeListFailure() {
//        TODO("Not yet implemented")
//    }

    private fun saveLevel(level: Int) {
        val sharedPreferences =
            activity?.getSharedPreferences("level", AppCompatActivity.MODE_PRIVATE)
        val editor = sharedPreferences!!.edit()
        editor.putString("level", level.toString())
        editor.apply()
    }

    private fun savePatchLevel(level: Int) {
        val sharedPreferences =
            activity?.getSharedPreferences("level", AppCompatActivity.MODE_PRIVATE)
        val editor = sharedPreferences!!.edit()
        editor.putString("level_patch", level.toString())
        editor.apply()
    }

    private fun getLevel(): String? {
        val sharedPreferences =
            activity?.getSharedPreferences("level", AppCompatActivity.MODE_PRIVATE)
        return sharedPreferences!!.getString("level", null)
    }

    private fun getPatchLevel(): String? {
        val sharedPreferences =
            activity?.getSharedPreferences("level", AppCompatActivity.MODE_PRIVATE)
        return sharedPreferences!!.getString("level_patch", null)
    }

    private fun getJwt(): String? {
        val sharedPreferences =
            activity?.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return sharedPreferences!!.getString("jwt", null)
    }

//    private fun updateChallenge(token: String, challengeId: Long) {
//        val challengeService = ChallengeService()
//        challengeService.setChallengeUpdateView(this)
//        challengeService.updateChallenge(token, challengeId)
//    }

//    override fun onChallengeUpdateSuccess(result: ChallengeLevelResponse) {
//
//        getChallengeList(getJwt()!!)
//
//        savePatchLevel(result.level)
//
//    }
//
//    override fun onChallengeUpdateFailure() {
//        TODO("Not yet implemented")
//    }
}