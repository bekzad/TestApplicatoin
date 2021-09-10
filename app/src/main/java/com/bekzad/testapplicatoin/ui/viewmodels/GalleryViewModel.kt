package com.bekzad.testapplicatoin.ui.viewmodels

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GalleryViewModel : ViewModel() {

    private val _imageUris = MutableLiveData<List<Uri>>()
    val imageUris: LiveData<List<Uri>> = _imageUris

    fun setUpImageUris(uriList: List<Uri>) {
        _imageUris.value = uriList
    }


}