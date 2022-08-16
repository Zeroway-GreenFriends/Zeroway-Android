package com.greenfriends.zeroway.ui.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.greenfriends.zeroway.GlideApp

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        GlideApp.with(view)
            .load(imageUrl)
            .into(view)
    }
}