package com.lbtt2801.hearme.data

import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.model.Artist

class ArtistsData {
    companion object{
        fun dataArtist(): ArrayList<Artist> {
            val dataArtist = ArrayList<Artist>()
            dataArtist.apply {
                add(
                    Artist(
                        "ar001", "Ariana Grande", R.drawable.img_artist, 0, true
                    )
                )
                add(
                    Artist(
                        "ar002", "The Weeknd", R.drawable.img_artist, 0, true
                    )
                )
                add(
                    Artist(
                        "ar003", "Acidrap", R.drawable.img_artist, 0, true
                    )
                )
                add(
                    Artist(
                        "ar004", "Ryan Jones", R.drawable.img_artist, 0, false
                    )
                )
                add(
                    Artist(
                        "ar005", "Jamie Gray", R.drawable.img_artist, 0, false
                    )
                )
                add(
                    Artist(
                        "ar006", "Troye Sivan", R.drawable.img_artist, 0, false
                    )
                )
            }
            return dataArtist
        }
    }
}