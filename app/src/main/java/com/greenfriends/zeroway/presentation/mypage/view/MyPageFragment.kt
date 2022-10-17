package com.greenfriends.zeroway.presentation.mypage.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.greenfriends.zeroway.R
import com.greenfriends.zeroway.databinding.FragmentMyPageBinding

class MyPageFragment : Fragment() {
    private lateinit var binding: FragmentMyPageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyPageBinding.inflate(inflater, container, false)

        binding.mypageNoticeTv.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.main_fl, NoticeFragment())
                ?.commitAllowingStateLoss()
        }

        binding.mypagePostCl.setOnClickListener {
            saveMyPage(0)
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.main_fl, MyPagePostFragment())
                ?.commitAllowingStateLoss()
        }

        binding.mypageCommentCl.setOnClickListener {
            saveMyPage(1)
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.main_fl, MyPagePostFragment())
                ?.commitAllowingStateLoss()
        }

        binding.mypageScrapCl.setOnClickListener {
            saveMyPage(2)
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.main_fl, MyPagePostFragment())
                ?.commitAllowingStateLoss()
        }

        binding.mypageLikeCl.setOnClickListener {
            saveMyPage(3)
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.main_fl, MyPagePostFragment())
                ?.commitAllowingStateLoss()
        }

        return binding.root
    }

    private fun saveMyPage(page: Int) {
        val sharedPreferences = activity?.getSharedPreferences("page", AppCompatActivity.MODE_PRIVATE)
        val editor = sharedPreferences!!.edit()
        editor.putInt("page", page)
        editor.apply()
    }
}