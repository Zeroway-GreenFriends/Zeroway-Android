package com.greenfriends.zeroway.presentation.home.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.greenfriends.zeroway.R
import com.greenfriends.zeroway.data.model.TermResponse
import com.greenfriends.zeroway.databinding.FragmentWordBinding
import com.greenfriends.zeroway.presentation.alarm.AlarmFragment
import com.greenfriends.zeroway.presentation.common.ViewModelFactory
import com.greenfriends.zeroway.presentation.home.WordDialogFragment
import com.greenfriends.zeroway.presentation.home.adapter.TermAdapter
import com.greenfriends.zeroway.presentation.home.adapter.TermSearchAdapter
import com.greenfriends.zeroway.presentation.home.viewmodel.HomeViewModel

class TermFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels { ViewModelFactory() }
    private lateinit var termAdapter: TermAdapter
    private lateinit var termSearchAdapter: TermSearchAdapter

    private lateinit var binding: FragmentWordBinding

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
        binding.wordSearchEt.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                getTermSearch(binding.wordSearchEt.text.toString(), null, null)
                //페이지 - (1)
                //사이즈 - (10) 한 페이지 당 몇개 보내는지
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                getTermSearch(binding.wordSearchEt.text.toString(), null, null)
            }

            override fun afterTextChanged(p0: Editable?) {
                getTermSearch(binding.wordSearchEt.text.toString(), null, null)
            }

        }
        )

        setObserve()
        setTermAdapter()
        setTermSearchAdapter()

        getTerm(null, null, null)

        return binding.root
    }

    private fun setObserve() {

        viewModel.tr.observe(
            viewLifecycleOwner
        ) { tr ->
            termAdapter.submitList(tr)
        }

        viewModel.termSearchResponse.observe(
            viewLifecycleOwner
        ) { termSearchResponse ->
            termSearchAdapter.submitList(termSearchResponse)
        }

    }

    private fun setTermAdapter() {
        termAdapter = TermAdapter(viewModel)
        binding.wordWordRv.adapter = termAdapter
        termAdapter.setMyItemClickListener(object : TermAdapter.MyItemClickListener {
            override fun onItemClick(word: TermResponse) {

                WordDialogFragment(word).show(
                    fragmentManager!!, "WordDialog"
                )
            }

        })
    }

    private fun setTermSearchAdapter() {
        termSearchAdapter = TermSearchAdapter(viewModel)
        binding.wordSearchRv.adapter = termSearchAdapter

        termSearchAdapter.setMyItemClickListener(object : TermSearchAdapter.MyItemClickListener {
            override fun onItemClick(word: TermResponse) {

                WordDialogFragment(word).show(
                    fragmentManager!!, "WordDialog"
                )
            }
        })
    }

    private fun getTerm(keyword: String?, page: Int?, size: Int?) {
        viewModel.getTerm(keyword, page, size)
    }

    private fun getTermSearch(keyword: String?, page: Int?, size: Int?) {
        viewModel.getTermSearch(keyword, page, size)
    }
}