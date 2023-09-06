package com.lbtt2801.hearme.data.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.time.LocalDate
import java.util.Date

@BindingAdapter("app:setImage")
fun setImage(imageView: ImageView, id: Int) {
    imageView.setImageResource(id)
}

@BindingAdapter("app:setDate")
fun setDate(text: TextView, date: Date) {
    val current = LocalDate.now()
//    if (current.compareTo(date) == 0) {
//        text.text = "Today"
//    }
}