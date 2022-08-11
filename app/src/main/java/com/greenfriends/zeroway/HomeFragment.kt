package com.greenfriends.zeroway

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.greenfriends.zeroway.api.AuthService
import com.greenfriends.zeroway.api.HomeService
import com.greenfriends.zeroway.api.TermView
import com.greenfriends.zeroway.api.TipView
import com.greenfriends.zeroway.data.TermResponse
import com.greenfriends.zeroway.data.TipResponse
import com.greenfriends.zeroway.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), TipView, TermView {
    private lateinit var binding: FragmentHomeBinding
    private var termDatas = ArrayList<TermResponse>()
    private var shopDatas = ArrayList<ShopList>()
    private var tipDatas = ArrayList<TipResponse>()
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
                ?.replace(R.id.main_fl, WordFragment())
                ?.commitAllowingStateLoss()
        }
        binding.homeWordMoreTv.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.main_fl, WordFragment())
                ?.commitAllowingStateLoss()
        }
        //환경용어 RecyclerView 연결
//        wordDatas.apply {
//            add(
//                WordList(
//                    "탄소 중립(炭素中立)",
//                    "carbon neutrality",
//                    "[환경] 탄소를 배출하는 만큼 그에 상응하는 조치를 취하여 실질 배출량을 ‘0’으로 만드는 일"
//                )
//            )
//            add(
//                WordList(
//                    "탄소 중립(炭素中立)",
//                    "carbon neutrality",
//                    "[환경] 탄소를 배출하는 만큼 그에 상응하는 조치를 취하여 실질 배출량을 ‘0’으로 만드는 일"
//                )
//            )
//            add(
//                WordList(
//                    "탄소 중립(炭素中立)",
//                    "carbon neutrality",
//                    "[환경] 탄소를 배출하는 만큼 그에 상응하는 조치를 취하여 실질 배출량을 ‘0’으로 만드는 일"
//                )
//            )
//        }

//        val termAdapter = TermAdapter(termDatas)
//        binding.homeWordRv.adapter = termAdapter
//        binding.homeWordRv.layoutManager =
//            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


        //제로웨이스트샵 RecyclerView 연결
        shopDatas.apply {
            add(ShopList("제로웨이스트 쑥", "4.2점", "(268명)"))
            add(ShopList("제로웨이스트 쑥", "4.2점", "(268명)"))
            add(ShopList("제로웨이스트 쑥", "4.2점", "(268명)"))
        }

        val shopAdapter = ShopAdapter(shopDatas)
        binding.homeShopRv.adapter = shopAdapter
        binding.homeShopRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        getTip()
        getTerm(null, null, null)

        //사용횟수 RecyclerView 연결
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

        return binding.root
    }

    private fun getTip() {
        val homeService = HomeService()
        homeService.setTipView(this)
        homeService.getTip()
    }

    override fun onTipSuccess(result: List<TipResponse>) {
        var count = 0
        for (i in result) {
            Log.e("i", i.toString())
            count++
            tipDatas.add(TipResponse("Tip ${count}", i.title, i.content))
        }
        val tipAdapter = TipAdapter(tipDatas)
        binding.homeTipRv.adapter = tipAdapter
        binding.homeTipRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

    }

    override fun onTipFailure() {
        TODO("Not yet implemented")
    }

    private fun getTerm(keyword: String?, page: Int?, size: Int?) {
        val homeService = HomeService()
        homeService.setTermView(this)
        homeService.getTerm(keyword, page, size)
    }

    override fun onTermSuccess(result: List<TermResponse>) {
        var cnt = 0
        for (i in result) {
            termDatas.add(i)
            cnt++
            if (cnt == 5) {
                break
            }
        }
        val termAdapter = TermAdapter(termDatas)
        binding.homeWordRv.adapter = termAdapter
        binding.homeWordRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

    }

    override fun onTermFailure() {
        TODO("Not yet implemented")
    }

}