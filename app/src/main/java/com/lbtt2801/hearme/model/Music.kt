package com.lbtt2801.hearme.model

import java.util.*

data class Music(
    val musicID: String,
    val musicName: String,
    val totalListeners: Int = 0,
    val image: String,
    val duration: Time,
    val release: Date,
    val category: Category,
    val artist: Artist,
    val isAlbum: Boolean,
    val information: String? = null,
    val path: Int ?= 1,
    var isPlaying: Boolean? = false
)
