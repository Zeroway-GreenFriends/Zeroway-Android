package com.greenfriends.zeroway.ui.home

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.greenfriends.zeroway.model.TermResponse
import com.greenfriends.zeroway.databinding.FragmentWordDialogBinding

class WordDialogFragment(val word: TermResponse): DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = true
    }

    private lateinit var binding: FragmentWordDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWordDialogBinding.inflate(inflater,container,false)

        //dialog 스크롤 변경
        //binding.wordDialogContent.movementMethod = ScrollingMovementMethod()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.wordDialogTitleTv.text = word.term
        binding.wordDialogContentTv.text = word.description

        binding.wordDialogCloseBtn.setOnClickListener {
            dismiss()
        }

    }
}