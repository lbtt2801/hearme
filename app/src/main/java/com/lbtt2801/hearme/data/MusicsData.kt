package com.lbtt2801.hearme.data

import android.content.ContentValues.TAG
import android.util.Log
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.model.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class MusicsData {
    companion object {
        fun dataMusic(): ArrayList<Music> {
            val dataMusic = ArrayList<Music>()
            val calToday = Calendar.getInstance()
            val calYesterday = Calendar.getInstance()
            calYesterday.add(Calendar.DATE, -1)

            dataMusic.apply {
                add(
                    Music(
                        "ms001",
                        "Shades of Love",
                        0,
                        R.drawable.img_music,
                        Time(0, 4, 30),
                        calToday.time,
                        CategoriesData.dataCategory().first { it.categoryID == "ca001" },
                        ArtistsData.dataArtist().first { it.artistId == "ar001" },
                        true
                    )
                )
                add(
                    Music(
                        "ms002",
                        "Without You",
                        55,
                        R.drawable.img_music,
                        Time(0, 4, 30),
                        Date("02/5/2023"),
                        CategoriesData.dataCategory().first { it.categoryID == "ca001" },
                        ArtistsData.dataArtist().first { it.artistId == "ar001" },
                        false
                    )
                )
                add(
                    Music(
                        "ms003",
                        "Save Your Tears",
                        4,
                        R.drawable.img_music,
                        Time(0, 4, 30),
                        calYesterday.time,
                        CategoriesData.dataCategory().first { it.categoryID == "ca005" },
                        ArtistsData.dataArtist().first { it.artistId == "ar001" },
                        true
                    )
                )
                add(
                    Music(
                        "ms004",
                        "Kiss Me More",
                        45,
                        R.drawable.img_music,
                        Time(0, 4, 30),
                        Date("01/08/2023"),
                        CategoriesData.dataCategory().first { it.categoryID == "ca005" },
                        ArtistsData.dataArtist().first { it.artistId == "ar002" },
                        false
                    )
                )
                add(
                    Music(
                        "ms005",
                        "Drivers License",
                        200,
                        R.drawable.img_music,
                        Time(0, 4, 30),
                        calYesterday.time,
                        CategoriesData.dataCategory().first { it.categoryID == "ca003" },
                        ArtistsData.dataArtist().first { it.artistId == "ar002" },
                        false
                    )
                )
                add(
                    Music(
                        "ms006",
                        "Forever After All",
                        600,
                        R.drawable.img_music,
                        Time(0, 4, 30),
                        Date("11/05/2023"),
                        CategoriesData.dataCategory().first { it.categoryID == "ca003" },
                        ArtistsData.dataArtist().first { it.artistId == "ar003" },
                        true
                    )
                )
                add(
                    Music(
                        "ms007",
                        "Break My Soul",
                        48,
                        R.drawable.img_music,
                        Time(0, 4, 30),
                        Date("12/07/2023"),
                        CategoriesData.dataCategory().first { it.categoryID == "ca004" },
                        ArtistsData.dataArtist().first { it.artistId == "ar003" },
                        false
                    )
                )
                add(
                    Music(
                        "ms008",
                        "Break My Soul",
                        120,
                        R.drawable.img_music,
                        Time(0, 4, 30),
                        calYesterday.time,
                        CategoriesData.dataCategory().first { it.categoryID == "ca004" },
                        ArtistsData.dataArtist().first { it.artistId == "ar005" },
                        true
                    )
                )
                add(
                    Music(
                        "ms009",
                        "593: Dallas Taylor | The Psychology of Sound Design",
                        200,
                        R.drawable.img_music,
                        Time(0, 4, 30),
                        calYesterday.time,
                        CategoriesData.dataCategory().first { it.categoryID == "ca002" },
                        ArtistsData.dataArtist().first { it.artistId == "ar008" },
                        true
                    )
                )
                add(
                    Music(
                        "ms010",
                        "621: Reid Hoffman | Surprising Entrepreneurial Truths",
                        500,
                        R.drawable.img_music,
                        Time(0, 4, 30),
                        calToday.time,
                        CategoriesData.dataCategory().first { it.categoryID == "ca002" },
                        ArtistsData.dataArtist().first { it.artistId == "ar007" },
                        false
                    )
                )
                add(
                    Music(
                        "ms011",
                        "Disater",
                        1050,
                        R.drawable.img_music,
                        Time(0, 4, 30),
                        Date("25/03/2023"),
                        CategoriesData.dataCategory().first { it.categoryID == "ca006" },
                        ArtistsData.dataArtist().first { it.artistId == "ar001" },
                        true
                    )
                )
                add(
                    Music(
                        "ms012",
                        "Handsome",
                        100,
                        R.drawable.img_music,
                        Time(0, 4, 30),
                        Date("13/04/2023"),
                        CategoriesData.dataCategory().first { it.categoryID == "ca006" },
                        ArtistsData.dataArtist().first { it.artistId == "ar002" },
                        true
                    )
                )
                add(
                    Music(
                        "ms013",
                        "Sharks",
                        200,
                        R.drawable.img_music,
                        Time(0, 4, 30),
                        Date("9/08/2023"),
                        CategoriesData.dataCategory().first { it.categoryID == "ca007" },
                        ArtistsData.dataArtist().first { it.artistId == "ar003" },
                        false
                    )
                )
                add(
                    Music(
                        "ms014",
                        "621: Reid Hoffman | Surprising Entrepreneurial Truths",
                        15,
                        R.drawable.img_music,
                        Time(0, 4, 30),
                        calToday.time,
                        CategoriesData.dataCategory().first { it.categoryID == "ca002" },
                        ArtistsData.dataArtist().first { it.artistId == "ar007" },
                        false
                    )
                )
                add(
                    Music(
                        "ms015",
                        "Pain",
                        692,
                        R.drawable.img_music,
                        Time(0, 4, 30),
                        Date("15/04/2023"),
                        CategoriesData.dataCategory().first { it.categoryID == "ca007" },
                        ArtistsData.dataArtist().first { it.artistId == "ar004" },
                        false
                    )
                )
                add(
                    Music(
                        "ms016",
                        "Motomami",
                        1203,
                        R.drawable.img_music,
                        Time(0, 4, 30),
                        calYesterday.time,
                        CategoriesData.dataCategory().first { it.categoryID == "ca008" },
                        ArtistsData.dataArtist().first { it.artistId == "ar003" },
                        true
                    )
                )
                add(
                    Music(
                        "ms017",
                        "Positions",
                        982,
                        R.drawable.img_music,
                        Time(0, 4, 30),
                        Date("11/09/2023"),
                        CategoriesData.dataCategory().first { it.categoryID == "ca008" },
                        ArtistsData.dataArtist().first { it.artistId == "ar002" },
                        false
                    )
                )
                add(
                    Music(
                        "ms018",
                        "Firework - Acoustic",
                        32,
                        R.drawable.img_music,
                        Time(0, 4, 30),
                        Date("06/03/2023"),
                        CategoriesData.dataCategory().first { it.categoryID == "ca009" },
                        ArtistsData.dataArtist().first { it.artistId == "ar001" },
                        false
                    )
                )
                add(
                    Music(
                        "ms019",
                        "Roar",
                        2031,
                        R.drawable.img_music,
                        Time(0, 4, 30),
                        calToday.time,
                        CategoriesData.dataCategory().first { it.categoryID == "ca009" },
                        ArtistsData.dataArtist().first { it.artistId == "ar001" },
                        true
                    )
                )
                add(
                    Music(
                        "ms020",
                        "Teenage Dream",
                        92,
                        R.drawable.img_music,
                        Time(0, 4, 30),
                        Date("24/03/2023"),
                        CategoriesData.dataCategory().first { it.categoryID == "ca010" },
                        ArtistsData.dataArtist().first { it.artistId == "ar006" },
                        false
                    )
                )
                add(
                    Music(
                        "ms021",
                        "Last Friday Night",
                        572,
                        R.drawable.img_music,
                        Time(0, 4, 30),
                        Date("01/02/2023"),
                        CategoriesData.dataCategory().first { it.categoryID == "ca010" },
                        ArtistsData.dataArtist().first { it.artistId == "ar002" },
                        false
                    )
                )
                add(
                    Music(
                        "ms022",
                        "487: Mike Rowe | Dirty Jobs",
                        42,
                        R.drawable.img_music,
                        Time(0, 4, 30),
                        Date("23/06/2023"),
                        CategoriesData.dataCategory().first { it.categoryID == "ca002" },
                        ArtistsData.dataArtist().first { it.artistId == "ar002" },
                        false
                    )
                )
                add(
                    Music(
                        "ms023",
                        "938: Tom Wright | Billion Dollar",
                        15,
                        R.drawable.img_music,
                        Time(0, 4, 30),
                        Date("05/02/2023"),
                        CategoriesData.dataCategory().first { it.categoryID == "ca002" },
                        ArtistsData.dataArtist().first { it.artistId == "ar007" },
                        false
                    )
                )
                add(
                    Music(
                        "ms024",
                        "610: Bill Sullivan | Pleased to Meet",
                        292,
                        R.drawable.img_music,
                        Time(0, 4, 30),
                        Date("10/05/2023"),
                        CategoriesData.dataCategory().first { it.categoryID == "ca002" },
                        ArtistsData.dataArtist().first { it.artistId == "ar008" },
                        false
                    )
                )
            }
            return dataMusic
        }
    }
}