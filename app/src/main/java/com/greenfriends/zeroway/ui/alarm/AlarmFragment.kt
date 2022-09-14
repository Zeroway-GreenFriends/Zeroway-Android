package com.greenfriends.zeroway.ui.alarm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.greenfriends.zeroway.model.AlarmList
import com.greenfriends.zeroway.databinding.FragmentAlarmBinding

class AlarmFragment : Fragment() {
    private lateinit var binding: FragmentAlarmBinding
    private var alarmDatas = ArrayList<AlarmList>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlarmBinding.inflate(inflater, container, false)

        //알람 RecyclerView 연결
        alarmDatas.apply {
            add(AlarmList("커뮤니티", "1시간 전", "‘쏘멍’ 님이 내 글에 댓글을 작성했습니다. "))
            add(AlarmList("커뮤니티", "1시간 전", "‘쏘멍’ 님이 내 글에 댓글을 작성했습니다. "))
            add(AlarmList("커뮤니티", "1시간 전", "‘쏘멍’ 님이 내 글에 댓글을 작성했습니다. "))
            add(AlarmList("커뮤니티", "1시간 전", "‘쏘멍’ 님이 내 글에 댓글을 작성했습니다. "))
        }

        val alarmAdapter = AlarmAdapter(alarmDatas)
        binding.alarmRv.adapter = alarmAdapter
        binding.alarmRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)


        return binding.root
    }
}