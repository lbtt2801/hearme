package com.lbtt2801.hearme.data.adapter

import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.databinding.BindingAdapter
import androidx.fragment.app.activityViewModels
import com.lbtt2801.hearme.model.Artist
import com.lbtt2801.hearme.viewmodel.ArtistViewModel
import com.lbtt2801.hearme.viewmodel.UserViewModel
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