package com.greenfriends.zeroway

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.greenfriends.zeroway.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private var wordDatas = ArrayList<WordList>()
    private var shopDatas = ArrayList<ShopList>()
    private var tipDatas = ArrayList<TipList>()
    private var useDatas = ArrayList<UseList>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

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
        binding.homeWordRv.adapter = wordAdapter
        binding.homeWordRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


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

        //실천팁 RecyclerView 연결
        tipDatas.apply {
            add(TipList("Tip 1", "비닐봉지, 일회용컵 등 일회용품 사용하지 않기를 실천해보아요."))
            add(TipList("Tip 2", "비닐봉지, 일회용컵 등 일회용품 사용하지 않기를 실천해보아요."))
            add(TipList("Tip 3", "비닐봉지, 일회용컵 등 일회용품 사용하지 않기를 실천해보아요."))
        }

        val tipAdapter = TipAdapter(tipDatas)
        binding.homeTipRv.adapter = tipAdapter
        binding.homeTipRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        //사용횟수 RecyclerView 연결
        useDatas.apply {
            add(UseList("15회 사용", "유리재질 텀블러"))
            add(UseList("15회 사용", "유리재질 텀블러"))
            add(UseList("15회 사용", "유리재질 텀블러"))
            add(UseList("15회 사용", "유리재질 텀블러"))
        }

        val useAdapter = UseAdapter(useDatas)
        binding.homeUseRv.adapter = useAdapter
        binding.homeUseRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        return binding.root
    }

}