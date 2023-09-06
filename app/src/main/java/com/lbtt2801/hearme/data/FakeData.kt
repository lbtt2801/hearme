package com.lbtt2801.hearme.data

import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.model.Artist
import com.lbtt2801.hearme.model.Category
import com.lbtt2801.hearme.model.Chart
import com.lbtt2801.hearme.model.Music
import com.lbtt2801.hearme.model.Time
import java.util.Date

class FakeData {
    companion object {
        fun dataMusic(): ArrayList<Music> {
            val dataMusic = ArrayList<Music>()
            dataMusic.apply {
                add(
                    Music(
                        "ms001",
                        "Shades of Love - Ania Szarmach",
                        0,
                        R.drawable.img_music,
                        Time(0, 4, 30),
                        Date("09/06/2023")
                    )
                )
                add(
                    Music(
                        "ms002",
                        "Without You - The Kid LAROI",
                        55,
                        R.drawable.img_music,
                        Time(0, 4, 30),
                        Date("02/12/2023")
                    )
                )
                add(
                    Music(
                        "ms003",
                        "Save Your Tears The Weeknd & Ari",
                        4,
                        R.drawable.img_music,
                        Time(0, 4, 30),
                        Date("03/10/2023")
                    )
                )
                add(
                    Music(
                        "ms004",
                        "Kiss Me More - Doja Cat Featuring",
                        45,
                        R.drawable.img_music,
                        Time(0, 4, 30),
                        Date("01/08/2023")
                    )
                )
                add(
                    Music(
                        "ms005",
                        "Drivers License - Olivia Rodrigo",
                        10,
                        R.drawable.img_music,
                        Time(0, 4, 30),
                        Date("11/02/2023")
                    )
                )
            }
            return dataMusic
        }

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

        fun dataChart(): ArrayList<Chart> {
            val dataChart = ArrayList<Chart>()
            dataChart.apply {
                add(
                    Chart(
                        "c001", "Top 100", R.drawable.img_chart
                    )
                )
                add(
                    Chart(
                        "c002", "Top 50", R.drawable.img_chart
                    )
                )
                add(
                    Chart(
                        "c003", "TOP ALBUMS GLOBAL", R.drawable.img_chart
                    )
                )
                add(
                    Chart(
                        "c004", "TOP SONGS GLOBAL", R.drawable.img_chart
                    )
                )
                add(
                    Chart(
                        "c005", "TOP SONGS UNITED STATES", R.drawable.img_chart
                    )
                )
            }
            return dataChart
        }

        fun dataCategory(): ArrayList<Category> {
            val dataCategory = ArrayList<Category>()
            dataCategory.apply {
                add(
                    Category(
                        "ca001",
                        "Chart",
                        R.drawable.img_artist,
                        R.color.green_500
                    )
                )
                add(
                    Category(
                        "ca002",
                        "Podcast",
                        R.drawable.img_artist,
                        R.color.purple_200
                    )
                )
                add(
                    Category(
                        "ca003",
                        "New Releases",
                        R.drawable.img_artist,
                        R.color.text_nav
                    )
                )
                add(
                    Category(
                        "ca004",
                        "Only You",
                        R.drawable.img_artist,
                        R.color.teal_700
                    )
                )
                add(
                    Category(
                        "ca005",
                        "Pop",
                        R.drawable.img_artist,
                        R.color.purple_700
                    )
                )
                add(
                    Category(
                        "ca006",
                        "K-Pop",
                        R.drawable.img_artist,
                        R.color.teal_200
                    )
                )
                add(
                    Category(
                        "ca007",
                        "Rock",
                        R.drawable.img_artist,
                        R.color.border_edt_pin
                    )
                )
                add(
                    Category(
                        "ca008",
                        "Hip-Hop",
                        R.drawable.img_artist,
                        R.color.purple_700
                    )
                )
                add(
                    Category(
                        "ca009",
                        "Jazz",
                        R.drawable.img_artist,
                        R.color.purple_500
                    )
                )
                add(
                    Category(
                        "ca010",
                        "Romace",
                        R.drawable.img_artist,
                        R.color.bg_button
                    )
                )
            }
            return dataCategory
        }
    }
}