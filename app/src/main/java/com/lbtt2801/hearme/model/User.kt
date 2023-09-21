package com.lbtt2801.hearme.model

import android.text.TextUtils
import android.util.Patterns
import com.lbtt2801.hearme.R
import java.util.Date

data class User(
    val email: String,
    var password: String,
    var secondaryEmail: String? = null,
    var avatar: Int = R.drawable.avatar_1,
    var fullName: String = "User 404",
    var nickName: String? = null,
    var dob: Date? = null,
    val nation: String? = null,
    var phone: String? = null,
    var pin: Int? = null,
    var gender: Boolean? = null,
    val isPremium: Boolean? = false,
    val isFirstSignIn: Boolean = true,
    val listArtistsFollowing: ArrayList<Artist> = ArrayList(), //Artist Following
    val listFollowers: ArrayList<User> = ArrayList(), //Followers
    val listMusicsLoved: ArrayList<Music> = ArrayList(), //Love
    val listMusicsListened: ArrayList<Music> = ArrayList(), //History
    val listMusicsDownloaded: ArrayList<Music> = ArrayList(), //Downloaded
    val listMusicsQueue: ArrayList<Music> = ArrayList(), //Queue
    val listPlaylist: ArrayList<Playlist> = ArrayList(), //Play list
    val blackListMusic: ArrayList<Music> = ArrayList(),
    val listUserFollowing: ArrayList<User> = ArrayList(), //User Following
) {
    val isDataValid: Boolean
        get() = (!TextUtils.isEmpty(email))
                && Patterns.EMAIL_ADDRESS.matcher(email).matches()
                && password.length >= 6
}
