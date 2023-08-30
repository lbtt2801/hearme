package com.lbtt2801.hearme.data

import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.model.User
import java.util.*
import kotlin.collections.ArrayList

class UserData {
    companion object {
        fun data(): ArrayList<User> {
            val data = ArrayList<User>()
            data.apply {
                add(
                    User(
                        "phuongviet.huit@gmail.com",
                        "Awdssdfg852456",
                        R.drawable.logo,
                        "Nguyen Phuong Viet",
                        "Viet Huit",
                        Date(2002, 12, 6, 0, 0, 0),
                        "VietNam",
                        "0939826376",
                        1234,
                        1023,
                        13,
                        true //true is male, false is female
                    )
                )
            }
            return data
        }
    }
}