package com.lbtt2801.hearme.model

import android.text.TextUtils
import android.util.Patterns
import java.util.Date

data class User(
    val email: String,
    val password: String,
    val avatar: Int,
    val fullName: String,
    val nickName: String,
    val dob: Date,
    val nation: String,
    val phone: String,
    val pin: Int,
    val numberOfFollowers: Int,
    val numberOfFollowing: Int,
    val gender: Boolean
) {
    val isDataValid: Boolean
        get() = (!TextUtils.isEmpty(email))
                && Patterns.EMAIL_ADDRESS.matcher(email).matches()
                && password.length >= 6
}
