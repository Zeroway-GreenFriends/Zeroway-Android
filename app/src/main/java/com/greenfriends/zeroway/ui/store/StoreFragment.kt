package com.greenfriends.zeroway.ui.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.greenfriends.zeroway.api.StoreListView
import com.greenfriends.zeroway.databinding.FragmentStoreBinding
import com.greenfriends.zeroway.model.StoreResponse
import com.greenfriends.zeroway.model.UseList
import com.greenfriends.zeroway.network.HomeService
import com.greenfriends.zeroway.network.StoreService
import com.greenfriends.zeroway.ui.UseAdapter

class StoreFragment : Fragment(), StoreListView {
    private lateinit var binding: FragmentStoreBinding
    private var storeList = ArrayList<StoreResponse>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStoreBinding.inflate(inflater, container, false)

        //사용횟수 RecyclerView 연결
//        storeList.apply {
//            add(
//                StoreResponse(
//                    "", "제목", "item", 5.0, "주소", "시간", "전화번호",
//                    "사이트", "인스타"
//                )
//            )
//            add(
//                StoreResponse(
//                    "", "제목", "item", 5.0, "주소", "시간", "전화번호",
//                    "사이트", "인스타"
//                )
//            )
//        }

        getStoreList(null,1,5)

        return binding.root
    }

    private fun getStoreList(keyword: String?, page: Int?, size: Int?) {
        val storeService = StoreService()
        storeService.setStoreListView(this)
        storeService.getStoreList(keyword, page, size)
    }

    override fun onStoreListSuccess(result: List<StoreResponse>) {
        for (i in result){
            storeList.add(i)
        }

        val storeAdapter = StoreAdapter(storeList)
        binding.storeRv.adapter = storeAdapter
        binding.storeRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    override fun onStoreListFailure() {
        TODO("Not yet implemented")
    }
}