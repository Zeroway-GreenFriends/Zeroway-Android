package com.greenfriends.zeroway.presentation.home.view

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.greenfriends.zeroway.R
import com.greenfriends.zeroway.data.model.TermResponse
import com.greenfriends.zeroway.data.model.UseList
import com.greenfriends.zeroway.databinding.FragmentHomeBinding
import com.greenfriends.zeroway.presentation.alarm.AlarmFragment
import com.greenfriends.zeroway.presentation.common.ViewModelFactory
import com.greenfriends.zeroway.presentation.home.WordDialogFragment
import com.greenfriends.zeroway.presentation.home.adapter.HomeStoreAdapter
import com.greenfriends.zeroway.presentation.home.adapter.TermAdapter
import com.greenfriends.zeroway.presentation.home.adapter.TipAdapter
import com.greenfriends.zeroway.presentation.home.adapter.UseAdapter
import com.greenfriends.zeroway.presentation.home.viewmodel.HomeViewModel
import com.greenfriends.zeroway.presentation.store.view.StoreFragment
import com.greenfriends.zeroway.presentation.store.viewmodel.StoreViewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels { ViewModelFactory() }
    private val store_vm: StoreViewModel by viewModels { ViewModelFactory() }

    private lateinit var termAdapter: TermAdapter
    private lateinit var tipAdapter: TipAdapter
    private lateinit var storeAdapter: HomeStoreAdapter

    private lateinit var binding: FragmentHomeBinding
    private var useDatas = ArrayList<UseList>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.homeAlarmIv.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.main_fl, AlarmFragment())
                ?.commitAllowingStateLoss()
        }

        binding.homeWordMoreIv.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.main_fl, TermFragment())
                ?.commitAllowingStateLoss()
        }
        binding.homeWordMoreTv.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.main_fl, TermFragment())
                ?.commitAllowingStateLoss()
        }

        binding.homeShopMoreIv.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.main_fl, StoreFragment())
                ?.commitAllowingStateLoss()
        }
        binding.homeShopMoreTv.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.main_fl, StoreFragment())
                ?.commitAllowingStateLoss()
        }

        setObserve()

        setTermAdapter()
        setTipAdapter()
        setStoreAdapter()
        Log.e("jwt",getJwt().toString())

        getChallenge()
        getStores(null,1,5)
        getTip()
        getTerm(null, null, null)

        setUseView()
        return binding.root
    }

    private fun setObserve() {
        viewModel.cr.observe(
            viewLifecycleOwner
        ) { cr ->
            binding.challengeResponse = cr
            Glide.with(binding.homeLevelIv.context)
                .load(Uri.parse(cr.imgUrl))
                .disallowHardwareConfig()
                .into(binding.homeLevelIv)
        }

        viewModel.tr.observe(
            viewLifecycleOwner
        ) { tr ->
            termAdapter.submitList(tr)
        }

        viewModel.tipResponse.observe(
            viewLifecycleOwner
        ) { tipResponse ->
            tipAdapter.submitList(tipResponse)
        }
    }

    private fun setTermAdapter() {
        termAdapter = TermAdapter(viewModel)
        termAdapter.setMyItemClickListener(object : TermAdapter.MyItemClickListener {

            override fun onItemClick(word: TermResponse) {

                Log.e("word",word.toString())
                WordDialogFragment(word).show(
                    fragmentManager!!, "WordDialog"
                )
            }
        })
        binding.homeWordRv.adapter = termAdapter
    }

    private fun setTipAdapter() {
        tipAdapter = TipAdapter(viewModel)
        binding.homeTipRv.adapter = tipAdapter
    }

    private fun setUseView() {
        useDatas.apply {
            add(UseList("15회 사용", "유리재질 텀블러"))
            add(UseList("17회 사용", "플라스틱 텀블러"))
            add(UseList("39회 사용", "세라믹 텀블러"))
            add(UseList("7100회 사용", "면재질 텀블러"))
        }

        val useAdapter = UseAdapter(useDatas)
        binding.homeUseRv.adapter = useAdapter
        binding.homeUseRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setStoreAdapter() {
        storeAdapter = HomeStoreAdapter()
        binding.homeShopRv.adapter = storeAdapter
        store_vm.stores.observe(
            viewLifecycleOwner
        ) { stores ->
            storeAdapter.submitStores(stores)
        }
    }


//    private fun getStoreList(keyword: String?, page: Int?, size: Int?) {
//        val storeService = StoreService()
//        storeService.setStoreListView(this)
//        storeService.getStoreList(keyword, page, size)
//    }
//
//    override fun onStoreListSuccess(result: List<StoreResponse>) {
//        for (i in result){
//            if (i.imageUrl != null){
//                homeStoreList.add(ShopList(i.name,i.imageUrl))
//            } else {
//                homeStoreList.add(ShopList(i.name,""))
//            }
//        }
//
//        Log.e("list",homeStoreList.toString())
//        val shopAdapter = ShopAdapter(homeStoreList)
//        binding.homeShopRv.adapter = shopAdapter
//        binding.homeShopRv.layoutManager =
//            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//    }
//
//    override fun onStoreListFailure() {
//        TODO("Not yet implemented")
//    }

    private fun getChallenge() {
        viewModel.getUserChallenge(getJwt()!!)
    }

    private fun getTerm(keyword: String?, page: Int?, size: Int?) {
        viewModel.getTerm(keyword, page, size)
    }

    private fun getTip() {
        viewModel.getTip()
    }

    private fun getStores(
        keyword: String? = null,
        page: Int = 1,
        size: Int = 5) {
        store_vm.getStores(keyword, page, size)
    }

    private fun getJwt(): String? {
        val sharedPreferences =
            activity?.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return sharedPreferences!!.getString("jwt", null)
    }
}