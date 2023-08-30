package com.lbtt2801.hearme.model

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
)
