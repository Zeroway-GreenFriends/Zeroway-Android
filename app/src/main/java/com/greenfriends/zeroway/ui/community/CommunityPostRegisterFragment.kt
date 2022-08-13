package com.greenfriends.zeroway.ui.community

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.greenfriends.zeroway.databinding.FragmentCommunityPostRegisterBinding
import com.greenfriends.zeroway.ui.common.ViewModelFactory

class CommunityPostRegisterFragment : Fragment() {

    private val viewModel: CommunityPostRegisterViewModel by viewModels { ViewModelFactory() }
    private lateinit var binding: FragmentCommunityPostRegisterBinding
    private lateinit var launcher: ActivityResultLauncher<Intent>
    private lateinit var adapter: CommunityPostRegisterAdapter

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
        setActivityResultLauncher()
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
}