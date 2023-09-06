package com.lbtt2801.hearme.data.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.lbtt2801.hearme.model.Time
import java.text.SimpleDateFormat
import java.util.Date

@BindingAdapter("app:setImage")
fun setImage(imageView: ImageView, id: Int) {
    imageView.setImageResource(id)
}

@BindingAdapter("app:setDate")
fun setDate(text: TextView, dateInput: Date) {
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    val date = Date()
    val current = formatter.format(date)
    val str = formatter.format(dateInput)
    if (current.compareTo(str) == 0)
        text.text = "Today"
    else text.text = "Yesterday"
}

@BindingAdapter("app:setDuration")
fun setDuration(text: TextView, time: Time) {
    text.text = "${time.minute}:${time.second} mins"
}