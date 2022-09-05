package com.greenfriends.zeroway.ui.community.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.gson.Gson
import com.greenfriends.zeroway.R
import com.greenfriends.zeroway.databinding.FragmentCommunityPostRegisterBinding
import com.greenfriends.zeroway.data.model.CommunityPostRegisterContentRequest
import com.greenfriends.zeroway.ui.common.ViewModelFactory
import com.greenfriends.zeroway.ui.community.adapter.CommunityPostRegisterAdapter
import com.greenfriends.zeroway.ui.community.viewmodel.CommunityPostRegisterViewModel
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class CommunityPostRegisterFragment : Fragment() {

    private val viewModel: CommunityPostRegisterViewModel by viewModels { ViewModelFactory() }
    private lateinit var binding: FragmentCommunityPostRegisterBinding
    private lateinit var launcher: ActivityResultLauncher<Intent>
    private lateinit var adapter: CommunityPostRegisterAdapter
    private val gson = Gson()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCommunityPostRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner

        setObserve()
        setCommunityPostRegisterAdapter()
        setChallengeClickListener()
        setAlbumClickListener()
        setPermission()
        setActivityResultLauncher()
        setPost()
    }

    private fun setObserve() {
        viewModel.isChallenge.observe(
            viewLifecycleOwner
        ) {
            binding.isChallenge = it
        }

        viewModel.imageUrl.observe(
            viewLifecycleOwner
        ) {
            adapter.submitList(it)
        }

        viewModel.setPostIsSuccess.observe(
            viewLifecycleOwner
        ) { isSuccess ->
            if (isSuccess != null) {
                if (isSuccess) {
                    Toast.makeText(requireContext(), "게시물 작성을 완료했습니다.", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(requireContext(), "게시물 작성을 실패했습니다.", Toast.LENGTH_SHORT).show()
                }
                parentFragmentManager.beginTransaction()
                    .replace(R.id.main_fl, CommunityFragment())
                    .commit()
            }
        }
    }

    private fun setCommunityPostRegisterAdapter() {
        adapter = CommunityPostRegisterAdapter()
        binding.communityPostRegisterRv.adapter = adapter
    }

    private fun setChallengeClickListener() {
        binding.communityPostRegisterChallengeTv.setOnClickListener {
            viewModel.setIsChallenge()
        }
    }

    private fun setAlbumClickListener() {
        binding.communityPostRegisterAlbumIv.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            intent.type = "image/*"
            launcher.launch(intent)
        }
    }

    private fun setPermission() {
        if (Build.VERSION.SDK_INT >= 30) {
            if (!Environment.isExternalStorageManager()) {
                val getPermission = Intent()
                getPermission.action = Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION;
                startActivity(getPermission);
            }
        }
    }

    private fun setActivityResultLauncher() {
        launcher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val data: Intent? = result.data
                    val uri = data?.data as Uri
                    viewModel.setImageUrl(getAbsolutelyPath(uri, requireContext()))
                }
            }
    }

    private fun getAbsolutelyPath(path: Uri?, context: Context): String {
        val proj: Array<String> = arrayOf(MediaStore.Images.Media.DATA)
        val cursor: Cursor? = context.contentResolver.query(path!!, proj, null, null, null)
        val index = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor?.moveToFirst()
        val result = cursor?.getString(index!!)

        return result!!
    }

    private fun getJwt(): String? {
        val sharedPreferences =
            activity?.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return sharedPreferences!!.getString("jwt", null)
    }

    private fun setPost() {
        binding.communityPostRegisterFab.setOnClickListener {
            val content =
                CommunityPostRegisterContentRequest(
                    binding.communityPostRegisterContentTv.text.toString(),
                    viewModel.getIsChallenge()!!
                )
            val imageUrls = viewModel.getImageUrls()
            val postContentRequestBody =
                gson.toJson(content).toRequestBody("application/json; charset=utf-8".toMediaType())
            val postImageUrls = mutableListOf<MultipartBody.Part>()
            if (!imageUrls.isNullOrEmpty()) {
                for (i in imageUrls.indices) {
                    val file = File(imageUrls[i])
                    val postImageUrlRequestBody =
                        file.asRequestBody("image/jpeg".toMediaType())
                    val postImageUrlMultipartBodyPart = MultipartBody.Part.createFormData(
                        "images",
                        file.name,
                        postImageUrlRequestBody
                    )
                    postImageUrls.add(postImageUrlMultipartBodyPart)
                }
            }

            viewModel.setPost(getJwt()!!, postImageUrls.toList(), postContentRequestBody)
        }
    }
}