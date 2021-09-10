package com.bekzad.testapplicatoin.ui.gallery

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bekzad.testapplicatoin.databinding.GalleryListItemBinding
import com.bumptech.glide.Glide

class GalleryAdapter : RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {

    var data = listOf<Uri>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class GalleryViewHolder(private var binding: GalleryListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(uri: Uri) {
            Glide.with(binding.image.context).load(uri).into(binding.image)
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): GalleryViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = GalleryListItemBinding.inflate(layoutInflater,
                parent, false)
                return GalleryViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        return GalleryViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val uri = data[position]
        holder.bind(uri)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}