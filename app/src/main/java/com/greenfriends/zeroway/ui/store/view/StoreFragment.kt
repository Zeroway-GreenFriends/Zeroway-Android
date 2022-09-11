package com.greenfriends.zeroway.ui.store.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.greenfriends.zeroway.databinding.FragmentStoreBinding
import com.greenfriends.zeroway.ui.common.ViewModelFactory
import com.greenfriends.zeroway.ui.store.adapter.StoreAdapter
import com.greenfriends.zeroway.ui.store.viewmodel.StoreViewModel

class StoreFragment : Fragment() {

    private val viewModel: StoreViewModel by viewModels { ViewModelFactory() }
    private lateinit var binding: FragmentStoreBinding
    private lateinit var adapter: StoreAdapter
    private var isLoading: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner

        getStores(isInit = true)
        setStoreAdapter()
        setOnClickListener()
        setOnScrollChangeListener()
    }

    private fun getStores(
        keyword: String? = null,
        page: Int = 1,
        size: Int = 5,
        isInit: Boolean = false
    ) {
        when (isInit) {
            true -> viewModel.getStores(keyword, page, size)
            else -> {
                adapter.setLoading(true)
                val handler = Handler(Looper.getMainLooper())
                handler.postDelayed(
                    {
                        val isFinish = adapter.setLoading(false)
                        if (isFinish) {
                            viewModel.getStores(keyword, page, size)
                        }
                    }, 1000
                )
            }
        }
    }

    private fun setStoreAdapter() {
        adapter = StoreAdapter()
        binding.storeRv.adapter = adapter
        viewModel.stores.observe(
            viewLifecycleOwner
        ) { stores ->
            adapter.submitStores(stores)
            isLoading = false
        }
    }

    private fun setOnClickListener() {
        with(binding) {
            storeSearchBtn.setOnClickListener {
                adapter.clear()
                viewModel.setPage(1)
                viewModel.setKeyword(storeSearchEt.text.toString())
                getStores(viewModel.getKeyword(), viewModel.getPage()!!)
            }
        }
    }

    private fun setOnScrollChangeListener() {
        binding.storeRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val totalItemCount = recyclerView.adapter!!.itemCount
                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                if (!recyclerView.canScrollVertically(1) && lastVisibleItemPosition == totalItemCount - 1 && !isLoading) {
                    isLoading = true
                    viewModel.setPage(viewModel.getPage()!! + 1)
                    getStores(viewModel.getKeyword(), viewModel.getPage()!!)
                }
            }
        })
    }
}