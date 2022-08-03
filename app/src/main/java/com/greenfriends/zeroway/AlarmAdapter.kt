package com.greenfriends.zeroway


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.greenfriends.zeroway.databinding.ItemAlarmBinding
import com.greenfriends.zeroway.databinding.ItemHomeShopBinding
import com.greenfriends.zeroway.databinding.ItemHomeTipBinding
import com.greenfriends.zeroway.databinding.ItemHomeWordBinding


class AlarmAdapter(private val alarmList: ArrayList<AlarmList>) :
    RecyclerView.Adapter<AlarmAdapter.ViewHolder>() {

    interface MyItemClickListener {
        fun onItemClick(alarm: AlarmList)
    }

    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): AlarmAdapter.ViewHolder {
        val binding: ItemAlarmBinding =
            ItemAlarmBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlarmAdapter.ViewHolder, position: Int) {
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