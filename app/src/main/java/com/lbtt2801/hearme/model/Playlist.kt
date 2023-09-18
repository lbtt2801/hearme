package com.lbtt2801.hearme.model

class Playlist(
    val playlistID: String,
    val playlistName: String,
    val image: Int,
    val listMusic: ArrayList<Music> =  ArrayList(),
    val size: String = listMusic.size.toString()
)