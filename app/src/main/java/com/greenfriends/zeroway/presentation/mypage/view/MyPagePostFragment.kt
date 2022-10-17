package com.greenfriends.zeroway.presentation.mypage.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.greenfriends.zeroway.R
import com.greenfriends.zeroway.data.model.MyPostList
import com.greenfriends.zeroway.data.model.MyPostResponse
import com.greenfriends.zeroway.data.model.NoticeResponse
import com.greenfriends.zeroway.databinding.FragmentMyPagePostBinding
import com.greenfriends.zeroway.presentation.common.ViewModelFactory
import com.greenfriends.zeroway.presentation.mypage.adapter.MyPostAdapter
import com.greenfriends.zeroway.presentation.mypage.adapter.NoticeAdapter
import com.greenfriends.zeroway.presentation.mypage.viewmodel.MyPageViewModel

class MyPagePostFragment : Fragment() {

    private val viewModel: MyPageViewModel by activityViewModels { ViewModelFactory() }

    private lateinit var binding: FragmentMyPagePostBinding
    private lateinit var myPostAdapter: MyPostAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyPagePostBinding.inflate(inflater, container, false)

        setObserve()
        setMyPostAdapter()

        when (getMyPage()){
            0 -> {
                getMyPost(getJwt()!!,null,null)
            }
            1 -> {
                getMyComment(getJwt()!!,null,null)
            }
            2 -> {
                getMyScrap(getJwt()!!,null,null)
            }
            3 -> {
                getMyLike(getJwt()!!,null,null)
            }
        }


        return binding.root
    }

    private fun setObserve() {
        viewModel.myPostResponse.observe(
            viewLifecycleOwner
        ) { nr ->
            myPostAdapter.submitList(nr)
        }

    }

    private fun setMyPostAdapter() {
        myPostAdapter = MyPostAdapter(viewModel)
        myPostAdapter.setMyItemClickListener(object : MyPostAdapter.MyItemClickListener {

            override fun onItemClick(post: MyPostList) {
            }

        })
        binding.mypostRv.adapter = myPostAdapter
    }

    private fun getMyPost(accessToken: String, page: Long?, size: Long?) {
        viewModel.getMyPost(accessToken, page, size)
    }

    private fun getMyComment(accessToken: String, page: Long?, size: Long?) {
        viewModel.getMyComment(accessToken, page, size)
    }

    private fun getMyScrap(accessToken: String, page: Long?, size: Long?) {
        viewModel.getMyScrap(accessToken, page, size)
    }

    private fun getMyLike(accessToken: String, page: Long?, size: Long?) {
        viewModel.getMyLike(accessToken, page, size)
    }

    private fun getJwt(): String? {
        val sharedPreferences =
            activity?.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return sharedPreferences!!.getString("jwt", null)
    }

    private fun getMyPage(): Int {
        val sharedPreferences =
            activity?.getSharedPreferences("page", AppCompatActivity.MODE_PRIVATE)
        return sharedPreferences!!.getInt("page", 6)
    }

}