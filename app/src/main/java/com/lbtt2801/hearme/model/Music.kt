package com.lbtt2801.hearme.model

import java.util.Date

data class Music(
    val musicID: String,
    val musicName: String,
    val totalListeners: Int = 0,
    val image: Int,
    val duration: Time,
    val release: Date,
    val category: Category,
    val artist: Artist,
    val isAlbum: Boolean
//    var isLoveList: Boolean = false,
//    var isPlayList: Boolean = false,
//    var isDownList: Boolean = false
)
