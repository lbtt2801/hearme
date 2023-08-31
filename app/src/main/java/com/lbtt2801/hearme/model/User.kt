package com.lbtt2801.hearme.model

import android.text.TextUtils
import android.util.Patterns
import java.util.Date

data class User(
    val email: String,
    val password: String,
    val avatar: Int? = null,
    val fullName: String? = null,
    val nickName: String? = null,
    val dob: Date? = null,
    val nation: String? = null,
    val phone: String? = null,
    val pin: Int? = null,
    val numberOfFollowers: Int? = null,
    val numberOfFollowing: Int? = null,
    val gender: Boolean? = null,
    val isPremium: Boolean? = false,
    val isFirstSignIn: Boolean = true,
    val listArtistsFollowing: ArrayList<Artist>? = null, //Following
    val listFollowers: ArrayList<User>? = null, //Followers
    val listMusicsLoved: ArrayList<Music>? = null,
    val listMusicListened: ArrayList<Music>? = null,
    val listMusicDownloaded: ArrayList<Music>? = null
) {
    val isDataValid: Boolean
        get() = (!TextUtils.isEmpty(email))
                && Patterns.EMAIL_ADDRESS.matcher(email).matches()
                && password.length >= 6
}