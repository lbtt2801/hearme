package com.lbtt2801.hearme.data

import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.model.Category

class CategoriesData {
    companion object {
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
                        R.color.greyscale_50
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
                        R.color.greyscale_200
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
                        "Romance",
                        R.drawable.img_artist,
                        R.color.bg_button
                    )
                )
            }
            return dataCategory
        }
    }
}