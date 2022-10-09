package com.greenfriends.zeroway.presentation.alarm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.greenfriends.zeroway.data.model.AlarmList
import com.greenfriends.zeroway.databinding.ItemAlarmBinding

class AlarmAdapter(private val alarmList: ArrayList<AlarmList>) :
    RecyclerView.Adapter<AlarmAdapter.ViewHolder>() {

    interface MyItemClickListener {
        fun onItemClick(alarm: AlarmList)
    }

    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemAlarmBinding =
            ItemAlarmBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            mItemClickListener.onItemClick(alarmList[position])
        }
    }

    override fun getItemCount(): Int {
        return alarmList.size
    }

    inner class ViewHolder(val binding: ItemAlarmBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(alarm: AlarmList) {
            binding.itemAlarmTitleTv.text = alarm.title
            binding.itemAlarmContentTv.text = alarm.content
            binding.itemAlarmTimeTv.text = alarm.time
        }
    }
}