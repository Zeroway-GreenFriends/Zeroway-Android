package com.greenfriends.zeroway.presentation.store.view

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
import com.greenfriends.zeroway.R
import com.greenfriends.zeroway.databinding.FragmentStoreBinding
import com.greenfriends.zeroway.presentation.common.EventObserve
import com.greenfriends.zeroway.presentation.common.STORE_ID
import com.greenfriends.zeroway.presentation.common.ViewModelFactory
import com.greenfriends.zeroway.presentation.store.adapter.StoreAdapter
import com.greenfriends.zeroway.presentation.store.viewmodel.StoreViewModel

class StoreFragment : Fragment() {

    private val viewModel: StoreViewModel by viewModels { ViewModelFactory() }
    private lateinit var binding: FragmentStoreBinding
    private lateinit var adapter: StoreAdapter

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
        setObservers()
        setOnClickListener()
        setStoreAdapter()
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
                        adapter.setLoading(false)
                        viewModel.getStores(keyword, page, size)
                    }, 1000
                )
            }
        }
    }

    private fun setObservers() {
        viewModel.storePostDetailEvent.observe(
            viewLifecycleOwner, EventObserve { storeId ->
                startStorePostDetailFragment(storeId.toString())
            }
        )
    }

    private fun setOnClickListener() {
        with(binding) {
            storeSearchBtn.setOnClickListener {
                adapter.clear()
                with(viewModel) {
                    setLoading(true)
                    setPage(1)
                    setKeyword(storeSearchEt.text.toString())
                    storeSearchEt.text.clear()
                    getStores(getKeyword(), isInit = true)
                }
            }
        }
    }

    private fun setStoreAdapter() {
        adapter = StoreAdapter(viewModel)
        binding.storeRv.adapter = adapter
        viewModel.stores.observe(
            viewLifecycleOwner
        ) { stores ->
            adapter.submitStores(stores)
            viewModel.setLoading(false)
        }
    }

    private fun setOnScrollChangeListener() {
        binding.storeRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val totalItemCount = recyclerView.adapter!!.itemCount
                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                with(viewModel) {
                    if (!recyclerView.canScrollVertically(1) && lastVisibleItemPosition == totalItemCount - 1 && !getLoading()) {
                        setLoading(true)
                        setPage(getPage() + 1)
                        getStores(getKeyword(), getPage())
                    }
                }

            }
        })
    }

    private fun startStorePostDetailFragment(storeId: String) {
        val bundle = Bundle()
        bundle.putString(STORE_ID, storeId)

        val storePostDetailFragment = StorePostDetailFragment()
        storePostDetailFragment.arguments = bundle
        parentFragmentManager.beginTransaction()
            .replace(R.id.main_fl, storePostDetailFragment)
            .commit()
    }
}