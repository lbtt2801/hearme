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
                        Time(0, 3, 30),
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
                        Time(0, 5, 30),
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
                        Time(0, 3, 20),
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
                        Time(0, 2, 15),
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
                        Time(0, 4, 40),
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
                        Time(0, 5, 10),
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
                        Time(0, 5, 20),
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
                        Time(0, 48, 35),
                        calYesterday.time,
                        CategoriesData.dataCategory().first { it.categoryID == "ca002" },
                        ArtistsData.dataArtist().first { it.artistId == "ar008" },
                        false,
                        "Shaquille O'Neal (@SHAQ) has been retired from basketball for years, but he’s still got his irons in plenty of fires. Here, we’ll discuss everything from superheroes to law enforcement to business to sports to podcasting to — yes — the Flat Earth Theory. What We Discuss with Shaquille O'Neal: The real-world experience Shaq endured preparing for a political race in 2020. How Shaq assembled what he calls The Panel to help manage not only his career but all his important life decisions — and how you can do the same. Why Shaq doesn’t consider himself a celebrity (and why that still isn’t a good reason to bug him in the middle of dinner). How Shaq manages his emotions so he stays non-reactive on and off the court. Does Shaq really believe in the Flat Earth Theory?"
                    )
                )
                add(
                    Music(
                        "ms010",
                        "837: Amy Webb | Changing Lives with Synthetic Biology  ",
                        500,
                        R.drawable.img_music,
                        Time(0, 34, 54),
                        calToday.time,
                        CategoriesData.dataCategory().first { it.categoryID == "ca002" },
                        ArtistsData.dataArtist().first { it.artistId == "ar007" },
                        false,
                        "Shaquille O'Neal (@SHAQ) has been retired from basketball for years, but he’s still got his irons in plenty of fires. Here, we’ll discuss everything from superheroes to law enforcement to business to sports to podcasting to — yes — the Flat Earth Theory. What We Discuss with Shaquille O'Neal: The real-world experience Shaq endured preparing for a political race in 2020. How Shaq assembled what he calls The Panel to help manage not only his career but all his important life decisions — and how you can do the same. Why Shaq doesn’t consider himself a celebrity (and why that still isn’t a good reason to bug him in the middle of dinner). How Shaq manages his emotions so he stays non-reactive on and off the court. Does Shaq really believe in the Flat Earth Theory?"
                    )
                )
                add(
                    Music(
                        "ms011",
                        "Disater",
                        1050,
                        R.drawable.img_music,
                        Time(0, 5, 23),
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
                        Time(0, 4, 43),
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
                        Time(0, 3, 35),
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
                        Time(0, 28, 25),
                        calToday.time,
                        CategoriesData.dataCategory().first { it.categoryID == "ca002" },
                        ArtistsData.dataArtist().first { it.artistId == "ar007" },
                        false,
                        "Shaquille O'Neal (@SHAQ) has been retired from basketball for years, but he’s still got his irons in plenty of fires. Here, we’ll discuss everything from superheroes to law enforcement to business to sports to podcasting to — yes — the Flat Earth Theory. What We Discuss with Shaquille O'Neal: The real-world experience Shaq endured preparing for a political race in 2020. How Shaq assembled what he calls The Panel to help manage not only his career but all his important life decisions — and how you can do the same. Why Shaq doesn’t consider himself a celebrity (and why that still isn’t a good reason to bug him in the middle of dinner). How Shaq manages his emotions so he stays non-reactive on and off the court. Does Shaq really believe in the Flat Earth Theory?"
                    )
                )
                add(
                    Music(
                        "ms015",
                        "Pain",
                        692,
                        R.drawable.img_music,
                        Time(0, 3, 23),
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
                        Time(0, 3, 43),
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
                        Time(0, 4, 23),
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
                        Time(0, 3, 56),
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
                        Time(0, 3, 23),
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
                        Time(0, 2, 34),
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
                        Time(0, 4, 45),
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
                        Time(0, 50, 53),
                        Date("23/06/2023"),
                        CategoriesData.dataCategory().first { it.categoryID == "ca002" },
                        ArtistsData.dataArtist().first { it.artistId == "ar002" },
                        false,
                        "Shaquille O'Neal (@SHAQ) has been retired from basketball for years, but he’s still got his irons in plenty of fires. Here, we’ll discuss everything from superheroes to law enforcement to business to sports to podcasting to — yes — the Flat Earth Theory. What We Discuss with Shaquille O'Neal: The real-world experience Shaq endured preparing for a political race in 2020. How Shaq assembled what he calls The Panel to help manage not only his career but all his important life decisions — and how you can do the same. Why Shaq doesn’t consider himself a celebrity (and why that still isn’t a good reason to bug him in the middle of dinner). How Shaq manages his emotions so he stays non-reactive on and off the court. Does Shaq really believe in the Flat Earth Theory?"
                    )
                )
                add(
                    Music(
                        "ms023",
                        "938: Tom Wright | Billion Dollar",
                        15,
                        R.drawable.img_music,
                        Time(0, 40, 33),
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
                        Time(0, 15, 35),
                        Date("10/05/2023"),
                        CategoriesData.dataCategory().first { it.categoryID == "ca002" },
                        ArtistsData.dataArtist().first { it.artistId == "ar008" },
                        false,
                        "Shaquille O'Neal (@SHAQ) has been retired from basketball for years, but he’s still got his irons in plenty of fires. Here, we’ll discuss everything from superheroes to law enforcement to business to sports to podcasting to — yes — the Flat Earth Theory. What We Discuss with Shaquille O'Neal: The real-world experience Shaq endured preparing for a political race in 2020. How Shaq assembled what he calls The Panel to help manage not only his career but all his important life decisions — and how you can do the same. Why Shaq doesn’t consider himself a celebrity (and why that still isn’t a good reason to bug him in the middle of dinner). How Shaq manages his emotions so he stays non-reactive on and off the court. Does Shaq really believe in the Flat Earth Theory?"
                    )
                )
                add(
                    Music(
                        "ms025",
                        "345: Ricardo Kaka | Nice to meet",
                        292,
                        R.drawable.img_music,
                        Time(0, 25, 35),
                        Date("15/07/2023"),
                        CategoriesData.dataCategory().first { it.categoryID == "ca002" },
                        ArtistsData.dataArtist().first { it.artistId == "ar007" },
                        false
                    )
                )
                add(
                    Music(
                        "ms026",
                        "239: Micheal Jack | Nightmare for kids",
                        292,
                        R.drawable.img_music,
                        Time(0, 32, 12),
                        Date("12/08/2023"),
                        CategoriesData.dataCategory().first { it.categoryID == "ca002" },
                        ArtistsData.dataArtist().first { it.artistId == "ar008" },
                        false,
                        "Shaquille O'Neal (@SHAQ) has been retired from basketball for years, but he’s still got his irons in plenty of fires. Here, we’ll discuss everything from superheroes to law enforcement to business to sports to podcasting to — yes — the Flat Earth Theory. What We Discuss with Shaquille O'Neal: The real-world experience Shaq endured preparing for a political race in 2020. How Shaq assembled what he calls The Panel to help manage not only his career but all his important life decisions — and how you can do the same. Why Shaq doesn’t consider himself a celebrity (and why that still isn’t a good reason to bug him in the middle of dinner). How Shaq manages his emotions so he stays non-reactive on and off the court. Does Shaq really believe in the Flat Earth Theory?"
                    )
                )
                add(
                    Music(
                        "ms027",
                        "588: Happy Bala | What are you doing?",
                        292,
                        R.drawable.img_music,
                        Time(0, 23, 32),
                        Date("05/09/2023"),
                        CategoriesData.dataCategory().first { it.categoryID == "ca002" },
                        ArtistsData.dataArtist().first { it.artistId == "ar009" },
                        false,
                        "Shaquille O'Neal (@SHAQ) has been retired from basketball for years, but he’s still got his irons in plenty of fires. Here, we’ll discuss everything from superheroes to law enforcement to business to sports to podcasting to — yes — the Flat Earth Theory. What We Discuss with Shaquille O'Neal: The real-world experience Shaq endured preparing for a political race in 2020. How Shaq assembled what he calls The Panel to help manage not only his career but all his important life decisions — and how you can do the same. Why Shaq doesn’t consider himself a celebrity (and why that still isn’t a good reason to bug him in the middle of dinner). How Shaq manages his emotions so he stays non-reactive on and off the court. Does Shaq really believe in the Flat Earth Theory?"
                    )
                )
            }
            return dataMusic
        }
    }
}