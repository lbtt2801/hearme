package com.lbtt2801.hearme.model

import android.text.TextUtils
import android.util.Patterns
import com.lbtt2801.hearme.R
import java.util.Date

data class User(
    val email: String,
    var password: String,
    var secondaryEmail: String? = null,
    val avatar: Int = R.drawable.logo,
    var fullName: String = "User 404",
    var nickName: String? = null,
    var dob: Date? = null,
    val nation: String? = null,
    var phone: String? = null,
    var pin: Int? = null,
    var numberOfFollowers: Int? = null,
    var numberOfFollowing: Int? = null,
    val gender: Boolean? = null,
    val isPremium: Boolean? = false,
    val isFirstSignIn: Boolean = true,
    val listArtistsFollowing: ArrayList<Artist> = ArrayList(), //Following
    val listFollowers: ArrayList<User>? = ArrayList(), //Followers
    val listMusicsLoved: ArrayList<Music>? = ArrayList(),
    val listMusicListened: ArrayList<Music>? = ArrayList(),
    val listMusicDownloaded: ArrayList<Music>? = ArrayList()
) {
    val isDataValid: Boolean
        get() = (!TextUtils.isEmpty(email))
                && Patterns.EMAIL_ADDRESS.matcher(email).matches()
                && password.length >= 6
}
