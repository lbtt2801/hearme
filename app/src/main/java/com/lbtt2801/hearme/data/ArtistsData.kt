package com.lbtt2801.hearme.data

import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.model.Artist

class ArtistsData {
    companion object {
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
                        "ar004",
                        "Ryan Jones",
                        R.drawable.img_artist,
                        0,
                        true,
                    )
                )
                add(
                    Artist(
                        "ar005",
                        "Jamie Gray",
                        R.drawable.img_artist,
                        0,
                        true,
                    )
                )
                add(
                    Artist(
                        "ar006",
                        "Troye Sivan",
                        R.drawable.img_artist,
                        0,
                        true,
                    )
                )
                add(
                    Artist(
                        "ar007",
                        "Apple Talk",
                        R.drawable.image_apple_talk,
                        0,
                        false,
                        "Gravis est aegrum curare, quam aegrum sequi, sed id futurum tempore, cum multum laboris et doloris est. Magnum hoc spectaculum et unum cacumen ostendit. Per id videre possumus eum esse doctorem disertum."
                    )
                )
                add(
                    Artist(
                        "ar008",
                        "Dr. Death",
                        R.drawable.image_dr_death,
                        0,
                        false,
                        "Gravis est aegrum curare, quam aegrum sequi, sed id futurum tempore, cum multum laboris et doloris est. Magnum hoc spectaculum et unum cacumen ostendit. Per id videre possumus eum esse doctorem disertum."
                    )
                )
                add(
                    Artist(
                        "ar009",
                        "The Jordan Harbinger Show",
                        R.drawable.image_jordan_show,
                        0,
                        false,
                        "Gravis est aegrum curare, quam aegrum sequi, sed id futurum tempore, cum multum laboris et doloris est. Magnum hoc spectaculum et unum cacumen ostendit. Per id videre possumus eum esse doctorem disertum."
                    )
                )
            }
            return dataArtist
        }
    }
}