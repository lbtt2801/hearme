package com.lbtt2801.hearme.data

import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.model.*
import java.util.*
import kotlin.collections.ArrayList

class MusicsData {
    companion object {
        fun dataMusic(): ArrayList<Music> {
            val dataMusic = ArrayList<Music>()
            dataMusic.apply {
                add(
                    Music(
                        "ms001",
                        "Shades of Love",
                        0,
                        R.drawable.img_music,
                        Time(0, 4, 30),
                        Date("09/07/2023"),
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
                        Date("03/8/2023"),
                        CategoriesData.dataCategory().first { it.categoryID == "ca003" },
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
                        CategoriesData.dataCategory().first { it.categoryID == "ca004" },
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
                        Date("11/02/2023"),
                        CategoriesData.dataCategory().first { it.categoryID == "ca005" },
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
                        CategoriesData.dataCategory().first { it.categoryID == "ca006" },
                        ArtistsData.dataArtist().first { it.artistId == "ar003" },
                        true
                    )
                )
                add(
                    Music(
                        "ms007",
                        "Break My Soul",
                        40,
                        R.drawable.img_music,
                        Time(0, 4, 30),
                        Date("12/07/2023"),
                        CategoriesData.dataCategory().first { it.categoryID == "ca007" },
                        ArtistsData.dataArtist().first { it.artistId == "ar003" },
                        false
                    )
                )
                add(
                    Music(
                        "ms008",
                        "593: Dallas Taylor | The Psychology of Sound Design",
                        200,
                        R.drawable.img_music,
                        Time(0, 4, 30),
                        Date("05/04/2023"),
                        CategoriesData.dataCategory().first { it.categoryID == "ca002" },
                        ArtistsData.dataArtist().first { it.artistId == "ar004" },
                        true
                    )
                )
                add(
                    Music(
                        "ms009",
                        "621: Reid Hoffman | Surprising Entrepreneurial Truths",
                        500,
                        R.drawable.img_music,
                        Time(0, 4, 30),
                        Date("01/05/2023"),
                        CategoriesData.dataCategory().first { it.categoryID == "ca002" },
                        ArtistsData.dataArtist().first { it.artistId == "ar005" },
                        false
                    )
                )
                add(
                    Music(
                        "ms010",
                        "Disater",
                        1050,
                        R.drawable.img_music,
                        Time(0, 4, 30),
                        Date("25/03/2023"),
                        CategoriesData.dataCategory().first { it.categoryID == "ca008" },
                        ArtistsData.dataArtist().first { it.artistId == "ar001" },
                        true
                    )
                )
                add(
                    Music(
                        "ms011",
                        "Handsome",
                        100,
                        R.drawable.img_music,
                        Time(0, 4, 30),
                        Date("13/04/2023"),
                        CategoriesData.dataCategory().first { it.categoryID == "ca008" },
                        ArtistsData.dataArtist().first { it.artistId == "ar002" },
                        false
                    )
                )
                add(
                    Music(
                        "ms012",
                        "Sharks",
                        200,
                        R.drawable.img_music,
                        Time(0, 4, 30),
                        Date("9/08/2023"),
                        CategoriesData.dataCategory().first { it.categoryID == "ca009" },
                        ArtistsData.dataArtist().first { it.artistId == "ar003" },
                        false
                    )
                )
            }
            return dataMusic
        }
    }
}