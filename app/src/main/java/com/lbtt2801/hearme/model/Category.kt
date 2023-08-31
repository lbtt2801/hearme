package com.lbtt2801.hearme.model

data class Category(
    val categoryID: String,
    val categoryName: String,
    val image: Int,
    val listMusic: ArrayList<Music>
)
