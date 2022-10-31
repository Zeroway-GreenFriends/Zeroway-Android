package com.greenfriends.zeroway.presentation.community.view

import android.content.Context
import android.graphics.Color
import android.graphics.Insets
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.greenfriends.zeroway.databinding.FragmentReportDialogBinding
import com.greenfriends.zeroway.presentation.common.EventObserve
import com.greenfriends.zeroway.presentation.common.ViewModelFactory
import com.greenfriends.zeroway.presentation.community.OnReportDialogClickListener
import com.greenfriends.zeroway.presentation.community.viewmodel.ReportDialogViewModel

class ReportDialogFragment : DialogFragment() {

    private val viewModel: ReportDialogViewModel by viewModels { ViewModelFactory() }
    private lateinit var binding: FragmentReportDialogBinding
    private lateinit var onReportDialogClickListener: OnReportDialogClickListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReportDialogBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.options = getOptions()
        setOnClickListener()
        setObserver()
    }

    override fun onResume() {
        super.onResume()

        val params: ViewGroup.LayoutParams? = dialog?.window?.attributes
        val windowManager = activity?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val size = windowManager.currentWindowMetricsPointCompat()
        val deviceWidth = size.x

        params?.width = (deviceWidth * 0.9).toInt()
        dialog?.window?.attributes = params as WindowManager.LayoutParams
    }

    private fun WindowManager.currentWindowMetricsPointCompat(): Point {
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            val windowInsets = currentWindowMetrics.windowInsets
            var insets: Insets = windowInsets.getInsets(WindowInsets.Type.navigationBars())
            windowInsets.displayCutout?.run {
                insets = Insets.max(
                    insets,
                    Insets.of(safeInsetLeft, safeInsetTop, safeInsetRight, safeInsetLeft)
                )
            }
            val insetsWidth = insets.right + insets.left
            val insetsHeight = insets.top + insets.bottom
            Point(
                currentWindowMetrics.bounds.width() - insetsWidth,
                currentWindowMetrics.bounds.height() - insetsHeight
            )
        } else {
            Point().apply {
                defaultDisplay.getSize(this)
            }
        }
    }

    fun setOnReportDialogClickListener(onReportDialogClickListener: OnReportDialogClickListener) {
        this.onReportDialogClickListener = onReportDialogClickListener
    }

    private fun setOnClickListener() {
        binding.reportDialogOption1Tv.setOnClickListener {
            viewModel.setOption(binding.reportDialogOption1Tv.text.toString())
        }
        binding.reportDialogOption2Tv.setOnClickListener {
            viewModel.setOption(binding.reportDialogOption2Tv.text.toString())
        }
        binding.reportDialogOption3Tv.setOnClickListener {
            viewModel.setOption(binding.reportDialogOption3Tv.text.toString())
        }
        binding.reportDialogOption4Tv.setOnClickListener {
            viewModel.setOption(binding.reportDialogOption4Tv.text.toString())
        }
        binding.reportDialogOption5Tv.setOnClickListener {
            viewModel.setOption(binding.reportDialogOption5Tv.text.toString())
        }
        binding.reportDialogCloseIv.setOnClickListener {
            onReportDialogClickListener.onSuccess(false, null)
            dismiss()
        }
        binding.reportDialogBtn.setOnClickListener {
            val option = viewModel.getOption()
            if (!option.isNullOrEmpty()) {
                onReportDialogClickListener.onSuccess(true, option)
            } else {
                onReportDialogClickListener.onSuccess(false, null)
            }
            dismiss()
        }
    }

    private fun setObserver() {
        viewModel.reportOptionSelectEvent.observe(
            viewLifecycleOwner, EventObserve { isSelected ->
                if (isSelected) {
                    binding.options = getOptions()
                }
            }
        )
    }

    private fun getOptions(): Map<String, Boolean> {
        return viewModel.getOptions()
    }
}