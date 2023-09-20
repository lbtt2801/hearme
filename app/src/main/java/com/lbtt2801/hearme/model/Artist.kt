package com.lbtt2801.hearme.model

data class Artist(
    val artistId: String,
    val artistName: String,
    val image: Int,
    var totalNumberOfListeners: Int = 0,
    val isSinger: Boolean = false,
    val information: String? = null
)
