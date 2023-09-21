package com.lbtt2801.hearme.data.adapter

import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.util.DisplayMetrics
import android.util.Log
import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatCheckedTextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.google.android.material.card.MaterialCardView
import com.google.android.material.imageview.ShapeableImageView
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.data.MusicsData
import com.lbtt2801.hearme.model.*
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@BindingAdapter("app:setImage")
fun setImage(imageView: ImageView, id: Int) {
    imageView.setImageResource(id)
//    imageView.background = ContextCompat.getDrawable(imageView.context, id)
    imageView.scaleType = ImageView.ScaleType.CENTER_CROP
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
        var isLove = false

        if (mainActivity.viewModelUser.isMusicInBlackList(
                mainActivity.email,
                music
            )
        ) {
            val builder = AlertDialog.Builder(checkBox.context)
            builder.apply {
                setTitle("Confirm")
                setMessage("${music.musicName} is putting into blacklist, are you want to remove it?")
                setPositiveButton(
                    "YES"
                ) { dialog, _ ->
                    mainActivity.viewModelUser.apply {
                        music.let {
                            updateBlackListMusic(
                                mainActivity.email,
                                it,
                                false
                            )
                            updateListMusicsLoved(
                                mainActivity.email,
                                it,
                                true
                            )
                        }
                    }
                    mainActivity.showSnack(
                        checkBox,
                        "You added ${music.musicName} to love list!"
                    )
                    dialog.dismiss()
                }
                setNegativeButton("NO") { dialog, _ ->
                    mainActivity.viewModelUser.updateListMusicsLoved(
                        mainActivity.email,
                        music,
                        false
                    )
                    dialog.dismiss()
                }
            }.create().show()
        } else {
            if (mainActivity.viewModelUser.lstDataUser.value?.first { it.email == mainActivity.email }?.listMusicsLoved?.none { it.musicID == music.musicID } == true) {
                isLove = true
                mainActivity.showSnack(
                    checkBox,
                    "You added ${music.musicName} to love list!"
                )
            } else {
                isLove = false
                mainActivity.showSnack(
                    checkBox,
                    "You removed ${music.musicName} from love list!"
                )
            }
            mainActivity.viewModelUser.updateListMusicsLoved(
                mainActivity.email,
                music,
                isLove
            )
        }
    }
}

@BindingAdapter("app:setPlayList")
fun setPlayList(checkBox: CheckBox, music: Music) {
//    val mainActivity = checkBox.context as MainActivity
//    val user =
//        mainActivity.viewModelUser.lstDataUser.value?.first { it.email == mainActivity.email }
//    mainActivity.viewModelUser.lstDataUser.observe(mainActivity) {
//        checkBox.isChecked =
//            user?.listPlayedMusic?.none { it.musicID == music.musicID } == false
//    }
}

@BindingAdapter("app:clickPlayList")
fun clickPlayList(checkBox: CheckBox, music: Music) {
    val mainActivity = checkBox.context as MainActivity
    checkBox.setOnClickListener() {
        var isLove = false

        if (mainActivity.viewModelUser.isMusicInBlackList(
                mainActivity.email,
                music
            )
        ) {
            val builder = AlertDialog.Builder(checkBox.context)
            builder.apply {
                setTitle("Confirm")
                setMessage("${music.musicName} is putting into blacklist, are you want to remove it?")
                setPositiveButton(
                    "YES"
                ) { dialog, _ ->
                    mainActivity.viewModelUser.apply {
                        music.let {
                            updateBlackListMusic(
                                mainActivity.email,
                                it,
                                false
                            )
                            updateListPlayedMusic(
                                mainActivity.email,
                                it,
                                true
                            )
                        }
                    }
                    mainActivity.showSnack(
                        checkBox,
                        "You added ${music.musicName} to playlist!"
                    )
                    dialog.dismiss()
                }
                setNegativeButton("NO") { dialog, _ ->
                    mainActivity.viewModelUser.updateListPlayedMusic(
                        mainActivity.email,
                        music,
                        false
                    )
                    dialog.dismiss()
                }
            }.create().show()
        } else {
//            if (mainActivity.viewModelUser.lstDataUser.value?.first { it.email == mainActivity.email }?.listPlayedMusic?.none { it.musicID == music.musicID } == true) {
//                isLove = true
//                mainActivity.showSnack(
//                    checkBox,
//                    "You added ${music.musicName} to playlist!"
//                )
//            } else {
//                isLove = false
//                mainActivity.showSnack(
//                    checkBox,
//                    "You removed ${music.musicName} from playlist!"
//                )
//            }
//            mainActivity.viewModelUser.updateListPlayedMusic(
//                mainActivity.email,
//                music,
//                isLove
//            )
        }
    }
}

@BindingAdapter("app:setDownList")
fun setDownList(checkBox: CheckBox, music: Music) {
    val mainActivity = checkBox.context as MainActivity
    val user =
        mainActivity.viewModelUser.lstDataUser.value?.first { it.email == mainActivity.email }
    mainActivity.viewModelUser.lstDataUser.observe(mainActivity) {
        checkBox.isChecked =
            user?.listMusicsDownloaded?.none { it.musicID == music.musicID } == false
    }
}

@BindingAdapter("app:clickDownList")
fun clickDownList(checkBox: CheckBox, music: Music) {
    val mainActivity = checkBox.context as MainActivity
    checkBox.setOnClickListener() {
        var isDownloaded = false

        if (mainActivity.viewModelUser.isMusicInBlackList(
                mainActivity.email,
                music
            )
        ) {
            val builder = AlertDialog.Builder(checkBox.context)
            builder.apply {
                setTitle("Confirm")
                setMessage("${music.musicName} is putting into blacklist, are you want to remove it?")
                setPositiveButton(
                    "YES"
                ) { dialog, _ ->
                    mainActivity.viewModelUser.apply {
                        music.let {
                            updateBlackListMusic(
                                mainActivity.email,
                                it,
                                false
                            )
                            updateListMusicsDownloaded(
                                mainActivity.email,
                                it,
                                true
                            )
                        }
                    }
                    mainActivity.showSnack(
                        checkBox,
                        "You added ${music.musicName} to downloaded list!"
                    )
                    dialog.dismiss()
                }
                setNegativeButton("NO") { dialog, _ ->
                    mainActivity.viewModelUser.updateListMusicsDownloaded(
                        mainActivity.email,
                        music,
                        false
                    )
                    dialog.dismiss()
                }
            }.create().show()
        } else {
            if (mainActivity.viewModelUser.lstDataUser.value?.first { it.email == mainActivity.email }?.listMusicsDownloaded?.none { it.musicID == music.musicID } == true) {
                isDownloaded = true
                mainActivity.showSnack(
                    checkBox,
                    "You added ${music.musicName} to downloaded list!"
                )
            } else {
                isDownloaded = false
                mainActivity.showSnack(
                    checkBox,
                    "You removed ${music.musicName} from downloaded list!"
                )
            }
            mainActivity.viewModelUser.updateListMusicsDownloaded(
                mainActivity.email,
                music,
                isDownloaded
            )
        }
    }
}

@BindingAdapter("app:setQueueList")
fun setQueueList(checkBox: CheckBox, music: Music) {
    val mainActivity = checkBox.context as MainActivity
    val user =
        mainActivity.viewModelUser.lstDataUser.value?.first { it.email == mainActivity.email }
    mainActivity.viewModelUser.lstDataUser.observe(mainActivity) {
        checkBox.isChecked =
            user?.listMusicsQueue?.none { it.musicID == music.musicID } == false
    }
}

@BindingAdapter("app:clickQueueList")
fun clickQueueList(checkBox: CheckBox, music: Music) {
    val mainActivity = checkBox.context as MainActivity
    checkBox.setOnClickListener() {
        var isQueued = false

        if (mainActivity.viewModelUser.isMusicInBlackList(
                mainActivity.email,
                music
            )
        ) {
            val builder = AlertDialog.Builder(checkBox.context)
            builder.apply {
                setTitle("Confirm")
                setMessage("${music.musicName} is putting into blacklist, are you want to remove it?")
                setPositiveButton(
                    "YES"
                ) { dialog, _ ->
                    mainActivity.viewModelUser.apply {
                        music.let {
                            updateBlackListMusic(
                                mainActivity.email,
                                it,
                                false
                            )
                            updateListMusicsQueued(
                                mainActivity.email,
                                it,
                                true
                            )
                        }
                    }
                    mainActivity.showSnack(
                        checkBox,
                        "You added ${music.musicName} to queued list!"
                    )
                    dialog.dismiss()
                }
                setNegativeButton("NO") { dialog, _ ->
                    mainActivity.viewModelUser.updateListMusicsQueued(
                        mainActivity.email,
                        music,
                        false
                    )
                    dialog.dismiss()
                }
            }.create().show()
        } else {
            if (mainActivity.viewModelUser.lstDataUser.value?.first { it.email == mainActivity.email }?.listMusicsQueue?.none { it.musicID == music.musicID } == true) {
                isQueued = true
                mainActivity.showSnack(
                    checkBox,
                    "You added ${music.musicName} to queued list!"
                )
            } else {
                isQueued = false
                mainActivity.showSnack(
                    checkBox,
                    "You removed ${music.musicName} from queued list!"
                )
            }
            mainActivity.viewModelUser.updateListMusicsQueued(
                mainActivity.email,
                music,
                isQueued
            )
        }
    }
}


@BindingAdapter("app:setFollowButton")
fun setFollowButton(toggleButton: ToggleButton, artist: Artist) {
    val mainActivity = toggleButton.context as MainActivity
    val user =
        mainActivity.viewModelUser.lstDataUser.value?.first { it.email == mainActivity.email }

    mainActivity.viewModelUser.lstDataUser.observe(mainActivity) {
        toggleButton.isChecked =
            user?.listArtistsFollowing?.none { it.artistId == artist.artistId } == false
    }
}

@BindingAdapter("app:clickFollowButton")
fun clickFollowButton(toggleButton: ToggleButton, artist: Artist) {
    val mainActivity = toggleButton.context as MainActivity

    toggleButton.setOnClickListener() {
        var isFollowing = false
        if (mainActivity.viewModelUser.lstDataUser.value?.first { it.email == mainActivity.email }?.listArtistsFollowing?.none { it.artistId == artist.artistId } == true) {
            isFollowing = true
            mainActivity.showSnack(
                toggleButton,
                "You following ${artist.artistName}!"
            )
        } else {
            isFollowing = false
            mainActivity.showSnack(
                toggleButton,
                "You unfollow ${artist.artistName}!"
            )
        }
        mainActivity.viewModelUser.updateFollowingArtistList(
            mainActivity.email,
            artist,
            isFollowing
        )
    }
}

@BindingAdapter("app:setUserFollowButton")
fun setUserFollowButton(toggleButton: ToggleButton, myFollower: User) {
    val mainActivity = toggleButton.context as MainActivity
    val myUser =
        mainActivity.viewModelUser.lstDataUser.value?.first { it.email == mainActivity.email }

    mainActivity.viewModelUser.lstDataUser.observe(mainActivity) { _ ->
        toggleButton.isChecked =
            myUser?.listUserFollowing?.any { it.email == myFollower.email } == true
    }
}

@BindingAdapter("app:clickUserFollowButton")
fun clickUserFollowButton(toggleButton: ToggleButton, myFollower: User) {
    val mainActivity = toggleButton.context as MainActivity
    val myUser =
        mainActivity.viewModelUser.lstDataUser.value?.first { it.email == mainActivity.email }

    toggleButton.setOnClickListener() {
        var isFollowing = false
        if (mainActivity.viewModelUser.lstDataUser.value?.first { it.email == myUser?.email }?.listUserFollowing?.none { it.email == myFollower.email } == true) {
            isFollowing = true
            mainActivity.showSnack(
                toggleButton,
                "You following ${myFollower.fullName}!"
            )
        } else {
            isFollowing = false
            mainActivity.showSnack(
                toggleButton,
                "You unfollow ${myFollower.fullName}!"
            )
        }
        mainActivity.viewModelUser.updateFollowingList(
            mainActivity.email,
            myFollower,
            isFollowing
        )
    }
}


@BindingAdapter("app:setPlay")
fun setPlay(checkBox: CheckBox, music: Music) {
    val mainActivity = checkBox.context as MainActivity

    mainActivity.viewModelUser.lstDataUser.observe(mainActivity) { _ ->
        checkBox.isChecked =
            music.isPlaying == true
    }
}

@BindingAdapter("app:clickPlayForCheckBox")
fun clickPlayForCheckBox(checkBox: CheckBox, music: Music) {
    val mainActivity = checkBox.context as MainActivity

    checkBox.setOnClickListener() {
        it.findNavController().navigate(R.id.songPlayFragment)
        var isPlaying = false
        if (mainActivity.viewModelMusic.lstDataMusics.value?.first { it.musicID == music.musicID }?.isPlaying == false) {
            isPlaying = true
            mainActivity.showSnack(
                checkBox,
                "You are playing ${music.musicName}!"
            )
            // Chuyễn trang và put bundle ở đây
        } else {
            isPlaying = false
            mainActivity.showSnack(
                checkBox,
                "You stop playing ${music.musicName}!"
            )
        }
        mainActivity.viewModelMusic.updatePlaying(
            music,
            isPlaying
        )
    }
}

@BindingAdapter("app:clickPlayForButton")
fun clickPlayForButton(appCompatButton: AppCompatButton, music: Music) {
    val mainActivity = appCompatButton.context as MainActivity

    appCompatButton.setOnClickListener() {
        it.findNavController().navigate(R.id.songPlayFragment)
        if (mainActivity.viewModelMusic.lstDataMusics.value?.first { it.musicID == music.musicID }?.isPlaying == false) {
            mainActivity.viewModelMusic.updatePlaying(
                music,
                true
            )
            mainActivity.showSnack(
                appCompatButton,
                "You are playing ${music.musicName}!"
            )
            // Chuyễn trang và put bundle ở đây
        } else {
            mainActivity.showSnack(
                appCompatButton,
                "You listening ${music.musicName}!"
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

@BindingAdapter("app:setTextYear")
fun setTextYear(text: TextView, date: Date) {
    val calendar = Calendar.getInstance()
    calendar.time = date
    text.text = calendar.get(Calendar.YEAR).toString()
}

@BindingAdapter("app:setWidthContainerAlbum")
fun setWidthContainerAlbum(shapeableImageView: ShapeableImageView, music: Music) {
    val displayMetrics = DisplayMetrics()
    val mainBinding = shapeableImageView.context as MainActivity
    mainBinding.windowManager.defaultDisplay.getMetrics(displayMetrics)
    Log.v(
        TAG,
        "Width: ${(displayMetrics.widthPixels / 2) - (24 * 2 + 12)} Height: ${displayMetrics.heightPixels}"
    )

    val params = shapeableImageView.layoutParams
    params.width = (displayMetrics.widthPixels / 2) - (24 * 2 + 25)
    params.height = (displayMetrics.widthPixels / 2) - (24 * 2 + 25)
    shapeableImageView.layoutParams = params
}

@BindingAdapter("app:changeFormatNumber")
fun changeFormatNumber(textView: TextView, number: Int) {
    val formatter: NumberFormat = DecimalFormat("#,###")
    val changedNumber = formatter.format(number)
    textView.text = "$changedNumber monthly listeners"
}

@BindingAdapter("app:setTextAlbums")
fun setTextAlbums(textView: TextView, playlist: Playlist) {
    textView.text = "${playlist.artist?.artistName}  |  ${playlist.release}"
}

@BindingAdapter("app:setTextSongs")
fun setTextSongs(textView: TextView, music: Music) {
    textView.text =
        "${music.artist.artistName}  |  ${music.duration.minute}:${music.duration.second} mins"
}

@BindingAdapter("app:setNumberOfFollowers")
fun setNumberOfFollowers(textView: TextView, arrayList: ArrayList<User>) {
    val mainActivity = textView.context as MainActivity
    mainActivity.viewModelUser.lstDataUser.observe(mainActivity) {
        textView.text = arrayList.size.toString()
    }
}

@BindingAdapter("app:setNumberOfUserFollowing")
fun setNumberOfUserFollowing(textView: TextView, arrayList: ArrayList<User>) {
    val mainActivity = textView.context as MainActivity
    mainActivity.viewModelUser.lstDataUser.observe(mainActivity) {
        textView.text = arrayList.size.toString()
    }
}

@BindingAdapter("app:setTextArtist")
fun setTextArtist(textView: TextView, artist: Artist) {
    val lstMusicsData = MusicsData.dataMusic()
    val lst = lstMusicsData.filter { it.artist.artistId == artist.artistId }
    if (artist.isSinger)
        textView.text = "${lst.size} Songs"
    else textView.text = "${lst.size} Episodes"
}