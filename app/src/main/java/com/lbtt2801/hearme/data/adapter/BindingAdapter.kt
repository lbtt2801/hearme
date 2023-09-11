package com.lbtt2801.hearme.data.adapter

import android.content.ContentValues.TAG
import android.graphics.Color
import android.util.Log
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.widget.AppCompatCheckedTextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.Observer
import com.google.android.material.card.MaterialCardView
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.model.Music
import com.lbtt2801.hearme.model.Time
import com.lbtt2801.hearme.model.TopicSearch
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
    val mainActivity = checkBox.context as MainActivity
    val user =
        mainActivity.viewModelUser.lstDataUser.value?.first { it.email == mainActivity.email }
    mainActivity.viewModelUser.lstDataUser.observe(mainActivity) {
        Log.v(TAG, "${it.first { it.email == mainActivity.email }.listPlayedMusic.size}")
        checkBox.isChecked =
            user?.listPlayedMusic?.none { it.musicID == music.musicID } == false
    }
}

@BindingAdapter("app:clickPlayList")
fun clickPlayList(checkBox: CheckBox, music: Music) {
    val mainActivity = checkBox.context as MainActivity
    checkBox.setOnClickListener() {
        mainActivity.viewModelUser.updateListPlayedMusic(
            mainActivity.email,
            music,
            checkBox.isChecked
        )
    }
}

@BindingAdapter("app:setDownList")
fun setDownList(checkBox: CheckBox, music: Music) {
    val mainActivity = checkBox.context as MainActivity
    val user =
        mainActivity.viewModelUser.lstDataUser.value?.first { it.email == mainActivity.email }
    mainActivity.viewModelUser.lstDataUser.observe(mainActivity) {
        Log.v(TAG, "${it.first { it.email == mainActivity.email }.listMusicsDownloaded.size}")
        checkBox.isChecked =
            user?.listMusicsDownloaded?.none { it.musicID == music.musicID } == false
    }
}

@BindingAdapter("app:clickDownList")
fun clickDownList(checkBox: CheckBox, music: Music) {
    checkBox.setOnCheckedChangeListener { _, _ ->
        val mainActivity = checkBox.context as MainActivity
        checkBox.setOnClickListener() {
            mainActivity.viewModelUser.updateListMusicsDownloaded(
                mainActivity.email,
                music,
                checkBox.isChecked
            )
        }
    }
}

@BindingAdapter("app:setBackgroundTint")
fun setBackgroundTint(cardView: MaterialCardView, color: Int) {
    cardView.backgroundTintList = ContextCompat.getColorStateList(cardView.context, color)
}

@BindingAdapter("app:topicSearchTextColor")
fun setColorTopicSearchText(checkedTextView: AppCompatCheckedTextView, topicSearch: TopicSearch) {
    if (checkedTextView.isChecked)
        checkedTextView.setTextColor(checkedTextView.resources.getColor(R.color.white, null))
    else
        checkedTextView.setTextColor(
            checkedTextView.resources.getColor(
                R.color.color_bg_button_continue,
                null
            )
        )
}

@BindingAdapter("app:onCheckedChanged")
fun onCheckedChanged(checkedTextView: AppCompatCheckedTextView, topicSearch: TopicSearch) {
    checkedTextView.setOnClickListener() {
        if (checkedTextView.isChecked) {
            checkedTextView.isChecked
            checkedTextView.setTextColor(checkedTextView.resources.getColor(R.color.white, null))
        } else {
            checkedTextView.isChecked = false
            checkedTextView.setTextColor(
                checkedTextView.resources.getColor(
                    R.color.color_bg_button_continue,
                    null
                )
            )
        }
    }
}