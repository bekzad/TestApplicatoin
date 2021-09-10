package com.bekzad.testapplicatoin.ui.gallery

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bekzad.testapplicatoin.databinding.FragmentGalleryBinding
import com.bekzad.testapplicatoin.ui.REQUEST_CODE_IMAGE
import com.bekzad.testapplicatoin.ui.viewmodels.GalleryViewModel

class GalleryFragment : Fragment() {

    private lateinit var binding: FragmentGalleryBinding
    private val viewModel by viewModels<GalleryViewModel>()
    private val adapter by lazy {
        GalleryAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGalleryBinding.inflate(inflater, container, false)
        getImagesFromGallery()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.imagesRv.apply {
            layoutManager = LinearLayoutManager(activity)

        }
        binding.imagesRv.adapter = adapter
        viewModel.imageUris.observe(viewLifecycleOwner, { uriList ->
            uriList?.let {
                adapter.data = it
            }
        })
    }

    private fun getImagesFromGallery() {
        val pickIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        pickIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)

        startActivityForResult(pickIntent, REQUEST_CODE_IMAGE)
    }

    /** Image selection */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if ((resultCode == Activity.RESULT_OK) and
            (requestCode == REQUEST_CODE_IMAGE)) {
                data?.let { handleImageRequestResult(data) }
        }
    }

    private fun handleImageRequestResult(intent: Intent) {
        val uriList: MutableList<Uri> = mutableListOf()
        val clipData = intent.clipData

        // If many images are selected
        if (clipData != null) {
            val count = clipData.itemCount
            for (i in 0 until count) {
                uriList.add(clipData.getItemAt(i).uri)
            }
        }
        // If only one image is selected
        else {
            val uri = intent.data
            if (uri == null) {
                Log.e("GalleryFragment", "Invalid Uri")
                return
            }
            uriList.add(uri)
        }
        viewModel.setUpImageUris(uriList)
    }


    companion object {
        fun newInstance() = GalleryFragment()
    }
}