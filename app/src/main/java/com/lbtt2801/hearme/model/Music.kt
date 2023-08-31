package com.lbtt2801.hearme.model

import java.util.Date

data class Music(
    val musicID: String,
    val musicName: String,
    val totalListeners: Int = 0,
    val image: Int,
    val duration: Int,
    val release: Date,
)
