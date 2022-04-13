package com.chunchiehliang.storagesample

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

/**
 * Basic [RecyclerView.ViewHolder] for our gallery.
 */
class ImageViewHolder(view: View, onClick: (MediaStoreImage) -> Unit) :
    RecyclerView.ViewHolder(view) {
    val rootView = view
    val imageView: ImageView = view.findViewById(R.id.image)

    init {
        imageView.setOnClickListener {
            val image = rootView.tag as? MediaStoreImage ?: return@setOnClickListener
            onClick(image)
        }
    }
}
