package com.lbtt2801.hearme.data.adapter

import android.content.ContentValues.TAG
import android.graphics.Color
import android.util.Log
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.Observer
import com.google.android.material.card.MaterialCardView
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.model.Music
import com.lbtt2801.hearme.model.Time
import com.lbtt2801.hearme.model.User
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

// CON DANG BUG
@BindingAdapter("app:setLoveList")
fun setLoveList(checkBox: CheckBox, music: Music) {
    val mainActivity = checkBox.context as MainActivity
    val user =
        mainActivity.viewModelUser.lstDataUser.value?.first { it.email == mainActivity.email }
    mainActivity.viewModelUser.lstDataUser.observe(mainActivity) {
        checkBox.isChecked =
            user?.listMusicsLoved?.none { it.musicID == music.musicID } == false
    }
}

@BindingAdapter("app:clickLoveList")
fun clickLoveList(checkBox: CheckBox, music: Music) {
    val mainActivity = checkBox.context as MainActivity
    checkBox.setOnClickListener() {
        mainActivity.viewModelUser.updateListMusicsLoved(
            mainActivity.email,
            music,
            checkBox.isChecked
        )
    }
}

@BindingAdapter("app:setPlayList")
fun setPlayList(checkBox: CheckBox, music: Music) {
    //Log.v(TAG, "setPlayList -> $music")

//    checkBox.isChecked = check
//    if (check) {
//        checkBox.buttonTintList =
//            ContextCompat.getColorStateList(checkBox.context, R.color.green_500)
//        checkBox.setButtonDrawable(R.drawable.ic_tick_square)
//    } else {
//        checkBox.buttonTintList = ContextCompat.getColorStateList(checkBox.context, R.color.text)
//        checkBox.setButtonDrawable(R.drawable.ic_add_playlist)
//    }
}

@BindingAdapter("app:clickPlayList")
fun clickPlayList(checkBox: CheckBox, music: Music) {
    checkBox.setOnCheckedChangeListener { _, _ ->
        // Log.v(TAG, "clickPlayList -> $music")
//        music.isPlayList = checkBox.isChecked
//        if (checkBox.isChecked) {
//            checkBox.buttonTintList =
//                ContextCompat.getColorStateList(checkBox.context, R.color.green_500)
//            checkBox.setButtonDrawable(R.drawable.ic_tick_square)
//        } else {
//            checkBox.buttonTintList =
//                ContextCompat.getColorStateList(checkBox.context, R.color.text)
//            checkBox.setButtonDrawable(R.drawable.ic_add_playlist)
//        }
    }
}

@BindingAdapter("app:setDownList")
fun setDownList(checkBox: CheckBox, music: Music) {
    //Log.v(TAG, "setDownList-> $music")
//    checkBox.isChecked = check
//    if (check) {
//        checkBox.buttonTintList =
//            ContextCompat.getColorStateList(checkBox.context, R.color.green_500)
//        checkBox.setButtonDrawable(R.drawable.ic_bold_down)
//    } else {
//        checkBox.buttonTintList = ContextCompat.getColorStateList(checkBox.context, R.color.text)
//        checkBox.setButtonDrawable(R.drawable.ic_light_down)
//    }
}

@BindingAdapter("app:clickDownList")
fun clickDownList(checkBox: CheckBox, music: Music) {
    checkBox.setOnCheckedChangeListener { _, _ ->
        //Log.v(TAG, "setDownList-> $music")
//        music.isDownList = checkBox.isChecked
//        if (checkBox.isChecked) {
//            checkBox.buttonTintList =
//                ContextCompat.getColorStateList(checkBox.context, R.color.green_500)
//            checkBox.setButtonDrawable(R.drawable.ic_bold_down)
//        } else {
//            checkBox.buttonTintList =
//                ContextCompat.getColorStateList(checkBox.context, R.color.text)
//            checkBox.setButtonDrawable(R.drawable.ic_light_down)
//        }
    }
}

@BindingAdapter("app:setBackgroundTint")
fun setBackgroundTint(cardView: MaterialCardView, color: Int) {
    cardView.backgroundTintList = ContextCompat.getColorStateList(cardView.context, color)
}