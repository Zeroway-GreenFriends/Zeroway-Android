package com.greenfriends.zeroway.ui.challenge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.greenfriends.zeroway.R
import com.greenfriends.zeroway.databinding.FragmentChallengeListBinding
import com.greenfriends.zeroway.model.ChallengeListResponse
import com.greenfriends.zeroway.model.ShopList
import com.greenfriends.zeroway.ui.ShopAdapter

class ChallengeListFragment : Fragment() {
    private lateinit var binding: FragmentChallengeListBinding

    private var challengeList = ArrayList<ChallengeListResponse>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChallengeListBinding.inflate(inflater, container, false)

        challengeList.apply {
            add(ChallengeListResponse(1,"cici",false))
            add(ChallengeListResponse(2,"icic",true))
        }
        val challengeListAdapter = ChallengeListAdapter(challengeList)
        binding.challengeListRv.adapter = challengeListAdapter
        binding.challengeListRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        return binding.root
    }
}