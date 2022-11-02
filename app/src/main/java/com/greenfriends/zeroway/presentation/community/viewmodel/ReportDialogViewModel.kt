package com.greenfriends.zeroway.presentation.community.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.greenfriends.zeroway.util.Event

class ReportDialogViewModel : ViewModel() {

    private var option: String? = null
    private val options = mutableMapOf(
        report_option_1 to false,
        report_option_2 to false,
        report_option_3 to false,
        report_option_4 to false,
        report_option_5 to false
    )

    private val _reportOptionSelectEvent = MutableLiveData<Event<Boolean>>()
    val reportOptionSelectEvent: LiveData<Event<Boolean>>
        get() = _reportOptionSelectEvent

    fun setOption(option: String) {
        for (key in options.keys) {
            options[key] = key == option
        }
        _reportOptionSelectEvent.value = Event(true)
        this.option = option
    }

    fun getOption(): String? {
        return option
    }

    fun getOptions(): Map<String, Boolean> {
        return options
    }

    companion object {
        private const val report_option_1 = "욕설/비하"
        private const val report_option_2 = "음란물/불건전한 만남 및 대화"
        private const val report_option_3 = "유출/사칭/사기"
        private const val report_option_4 = "상업적 광고 및 판매"
        private const val report_option_5 = "낚시/도배"
    }
}