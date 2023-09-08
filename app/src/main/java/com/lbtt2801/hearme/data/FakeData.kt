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
    }
}