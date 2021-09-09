package com.bekzad.testapplicatoin

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


/**
 * For getting an image from the specified url and setting it to that view
 */
@BindingAdapter("imageUrl")
fun ImageView.bindImage(imgUrl: String?) {
    println(imgUrl)
    imgUrl?.let {
        val uri = it.toUri().buildUpon().scheme("https").build()
        Glide.with(this.context)
            .load(uri)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(this)
    }
}

