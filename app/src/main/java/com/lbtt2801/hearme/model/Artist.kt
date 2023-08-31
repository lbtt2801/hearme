package com.lbtt2801.hearme.model

data class Artist(
    val artistId: String,
    val artistName: String,
    val image: Int,
    val totalNumberOfListeners: Int = 0,
    val isSinger: Boolean = false,
)
