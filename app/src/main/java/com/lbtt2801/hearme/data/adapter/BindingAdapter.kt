package com.lbtt2801.hearme.data.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("app:setImage")
fun setImage(imageView: ImageView, id: Int) {
    imageView.setImageResource(id)
}