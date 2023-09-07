package com.lbtt2801.hearme.data.adapter

import android.graphics.Color
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.google.android.material.card.MaterialCardView
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.model.Music
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

@BindingAdapter("app:setCategory")
fun setCategory(text: TextView, check: Boolean) {
    if (check)
        text.text = "Single"
    else
        text.text = "Album"
}

@BindingAdapter("app:setLoveList")
fun setLoveList(checkBox: CheckBox, check: Boolean) {
    checkBox.isChecked = check
    if (check) {
        checkBox.buttonTintList = ContextCompat.getColorStateList(checkBox.context, R.color.green_500)
        checkBox.setButtonDrawable(R.drawable.ic_bold_heart)
    }
    else {
        checkBox.buttonTintList = ContextCompat.getColorStateList(checkBox.context, R.color.text)
        checkBox.setButtonDrawable(R.drawable.ic_light_heart)
    }
}

@BindingAdapter("app:clickLoveList")
fun clickLoveList(checkBox: CheckBox, music: Music) {
    checkBox.setOnCheckedChangeListener { _, _ ->
        music.isLoveList = checkBox.isChecked
        if (checkBox.isChecked) {
            checkBox.buttonTintList = ContextCompat.getColorStateList(checkBox.context, R.color.green_500)
            checkBox.setButtonDrawable(R.drawable.ic_bold_heart)
        }
        else {
            checkBox.buttonTintList = ContextCompat.getColorStateList(checkBox.context, R.color.text)
            checkBox.setButtonDrawable(R.drawable.ic_light_heart)
        }
    }
}

@BindingAdapter("app:setPlayList")
fun setPlayList(checkBox: CheckBox, check: Boolean) {
    checkBox.isChecked = check
    if (check) {
        checkBox.buttonTintList = ContextCompat.getColorStateList(checkBox.context, R.color.green_500)
        checkBox.setButtonDrawable(R.drawable.ic_tick_square)
    }
    else {
        checkBox.buttonTintList = ContextCompat.getColorStateList(checkBox.context, R.color.text)
        checkBox.setButtonDrawable(R.drawable.ic_add_playlist)
    }
}

@BindingAdapter("app:clickPlayList")
fun clickPlayList(checkBox: CheckBox, music: Music) {
    checkBox.setOnCheckedChangeListener { _, _ ->
        music.isPlayList = checkBox.isChecked
        if (checkBox.isChecked) {
            checkBox.buttonTintList = ContextCompat.getColorStateList(checkBox.context, R.color.green_500)
            checkBox.setButtonDrawable(R.drawable.ic_tick_square)
        }
        else {
            checkBox.buttonTintList = ContextCompat.getColorStateList(checkBox.context, R.color.text)
            checkBox.setButtonDrawable(R.drawable.ic_add_playlist)
        }
    }
}

@BindingAdapter("app:setDownList")
fun setDownList(checkBox: CheckBox, check: Boolean) {
    checkBox.isChecked = check
    if (check) {
        checkBox.buttonTintList = ContextCompat.getColorStateList(checkBox.context, R.color.green_500)
        checkBox.setButtonDrawable(R.drawable.ic_bold_down)
    }
    else {
        checkBox.buttonTintList = ContextCompat.getColorStateList(checkBox.context, R.color.text)
        checkBox.setButtonDrawable(R.drawable.ic_light_down)
    }
}

@BindingAdapter("app:clickDownList")
fun clickDownList(checkBox: CheckBox, music: Music) {
    checkBox.setOnCheckedChangeListener { _, _ ->
        music.isDownList = checkBox.isChecked
        if (checkBox.isChecked) {
            checkBox.buttonTintList = ContextCompat.getColorStateList(checkBox.context, R.color.green_500)
            checkBox.setButtonDrawable(R.drawable.ic_bold_down)
        }
        else {
            checkBox.buttonTintList = ContextCompat.getColorStateList(checkBox.context, R.color.text)
            checkBox.setButtonDrawable(R.drawable.ic_light_down)
        }
    }
}

@BindingAdapter("app:setBackgroundTint")
fun setBackgroundTint(cardView: MaterialCardView, color: Int) {
    cardView.backgroundTintList = ContextCompat.getColorStateList(cardView.context, color)
}