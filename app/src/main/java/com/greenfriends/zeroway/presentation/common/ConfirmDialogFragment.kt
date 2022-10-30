package com.greenfriends.zeroway.presentation.common

import android.content.Context
import android.graphics.Color
import android.graphics.Insets
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.greenfriends.zeroway.databinding.FragmentConfirmDialogBinding

class ConfirmDialogFragment(
    private val title: String,
    private val subTitle: String,
) : DialogFragment() {

    private lateinit var binding: FragmentConfirmDialogBinding
    private lateinit var onConfirmDialogClickListener: OnConfirmDialogClickListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentConfirmDialogBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setText()
        setOnClickListener()
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

    fun setOnConfirmDialogClickListener(onConfirmDialogClickListener: OnConfirmDialogClickListener) {
        this.onConfirmDialogClickListener = onConfirmDialogClickListener
    }

    private fun setText() {
        binding.title = title
        binding.subTitle = subTitle
    }

    private fun setOnClickListener() {
        with(binding) {
            confirmDialogBtn.setOnClickListener {
                onConfirmDialogClickListener.isSuccess(true)
                dismiss()
            }
            confirmDialogCloseIv.setOnClickListener {
                onConfirmDialogClickListener.isSuccess(false)
                dismiss()
            }
        }
    }
}