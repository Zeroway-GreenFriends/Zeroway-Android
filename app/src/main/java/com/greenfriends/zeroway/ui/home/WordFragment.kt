package com.greenfriends.zeroway.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager

import com.greenfriends.zeroway.network.HomeService
import com.greenfriends.zeroway.api.TermSearchView
import com.greenfriends.zeroway.api.TermView
import com.greenfriends.zeroway.model.TermResponse
import com.greenfriends.zeroway.R
import com.greenfriends.zeroway.databinding.FragmentWordBinding
import com.greenfriends.zeroway.ui.alarm.AlarmFragment

class WordFragment : Fragment(), TermView, TermSearchView {
    private lateinit var binding: FragmentWordBinding
    private var wordDatas = ArrayList<TermResponse>()
    private var wordSearchDatas = ArrayList<TermResponse>()

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

        getTerm(null, null, null)

        binding.wordSearchEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                getTermSearch(binding.wordSearchEt.text.toString(), null, null)
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                getTermSearch(binding.wordSearchEt.text.toString(), null, null)
            }

            override fun afterTextChanged(p0: Editable?) {
                getTermSearch(binding.wordSearchEt.text.toString(), null, null)
            }

        }
        )
        return binding.root
    }

    private fun getTerm(keyword: String?, page: Int?, size: Int?) {
        val homeService = HomeService()
        homeService.setTermView(this)
        homeService.getTerm(keyword, page, size)
    }

    override fun onTermSuccess(result: List<TermResponse>) {
        var cnt = 0
        for (i in result) {
            wordDatas.add(i)
            cnt++
            if (cnt == 5) {
                break
            }
        }
        val termAdapter = TermAdapter(wordDatas)

        termAdapter.setMyItemClickListener(object : TermAdapter.MyItemClickListener {
            override fun onItemClick(word: TermResponse) {
                //dialog 띄우기
                WordDialogFragment(word).show(
                    fragmentManager!!, "WordDialog"
                )
            }

        })

        binding.wordWordRv.adapter = termAdapter
        binding.wordWordRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

    }

    override fun onTermFailure() {
        TODO("Not yet implemented")
    }


    private fun getTermSearch(keyword: String?, page: Int?, size: Int?) {
        val homeService = HomeService()
        homeService.setTermSerachView(this)
        homeService.getTermSearch(keyword, page, size)
    }

    override fun onTermSearchSuccess(result: List<TermResponse>) {
        wordSearchDatas.clear()
        for (i in result) {
            wordSearchDatas.add(i)
        }
        val termSearchAdapter = WordSearchAdapter(wordSearchDatas)

        termSearchAdapter.setMyItemClickListener(object : WordSearchAdapter.MyItemClickListener {
            override fun onItemClick(word: TermResponse) {
                //dialog 띄우기
                WordDialogFragment(word).show(
                    fragmentManager!!, "WordDialog"
                )
            }

        })

        binding.wordSearchRv.adapter = termSearchAdapter
        binding.wordSearchRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

    }

    override fun onTermSearchFailure() {
        TODO("Not yet implemented")
    }
}