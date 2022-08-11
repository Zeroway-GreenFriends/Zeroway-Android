package com.greenfriends.zeroway.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.greenfriends.zeroway.R
import com.greenfriends.zeroway.model.WordList
import com.greenfriends.zeroway.model.WordSearchList
import com.greenfriends.zeroway.databinding.FragmentWordBinding

class WordFragment : Fragment() {
    private lateinit var binding: FragmentWordBinding
    private var wordDatas = ArrayList<WordList>()
    private var wordSearchDatas = ArrayList<WordSearchList>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWordBinding.inflate(inflater, container, false)

        binding.wordAlarmIv.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.main_fl, AlarmFragment())
                ?.commitAllowingStateLoss()
        }

        //환경용어 RecyclerView 연결
        wordDatas.apply {
            add(
                WordList(
                    "탄소 중립(炭素中立)",
                    "carbon neutrality",
                    "[환경] 탄소를 배출하는 만큼 그에 상응하는 조치를 취하여 실질 배출량을 ‘0’으로 만드는 일"
                )
            )
            add(
                WordList(
                    "탄소 중립(炭素中立)",
                    "carbon neutrality",
                    "[환경] 탄소를 배출하는 만큼 그에 상응하는 조치를 취하여 실질 배출량을 ‘0’으로 만드는 일"
                )
            )
            add(
                WordList(
                    "탄소 중립(炭素中立)",
                    "carbon neutrality",
                    "[환경] 탄소를 배출하는 만큼 그에 상응하는 조치를 취하여 실질 배출량을 ‘0’으로 만드는 일"
                )
            )
        }

        val wordAdapter = WordAdapter(wordDatas)
        binding.wordWordRv.adapter = wordAdapter
        binding.wordWordRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


        //환경 용어 검색 RecyclerView 연결
        wordSearchDatas.apply {
            add(WordSearchList("[환경] 탄소 중립(炭素中立)"))
            add(WordSearchList("[환경] 탄소 중립(炭素中立)"))
            add(WordSearchList("[환경] 탄소 중립(炭素中立)"))
            add(WordSearchList("[환경] 탄소 중립(炭素中立)"))
            add(WordSearchList("[환경] 탄소 중립(炭素中立)"))
            add(WordSearchList("[환경] 탄소 중립(炭素中立)"))
            add(WordSearchList("[환경] 탄소 중립(炭素中立)"))
        }

        val wordSearchAdapter = WordSearchAdapter(wordSearchDatas)
        binding.wordSearchRv.adapter = wordSearchAdapter
        binding.wordSearchRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        return binding.root
    }
}