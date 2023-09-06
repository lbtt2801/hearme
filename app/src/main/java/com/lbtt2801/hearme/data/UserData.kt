package com.lbtt2801.hearme.data

import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.model.User
import java.util.*
import kotlin.collections.ArrayList

class UserData {
//    var dataUser = ArrayList<User>()
//
//    init {
//        dataUser.apply {
//            add(
//                User(
//                    "phuongviet.huit@gmail.com",
//                    "Awdssdfg852456",
//                    R.drawable.logo,
//                    "Nguyen Phuong Viet",
//                    "Viet Huit",
//                    Date(2002, 12, 6, 0, 0, 0),
//                    "VietNam",
//                    "0939826376",
//                    1234,
//                    1023,
//                    13,
//                    gender = true, //true is male, false is female
//                    isFirstSignIn = false // true if user the first sign in
//                )
//            )
//            add(
//                User(
//                    "lebuitantruong@gmail.com",
//                    "lbtt2801",
//                    R.drawable.logo,
//                    "Le Bui Tan Truong",
//                    "Truong HUIT",
//                    Date(2002, 1, 28, 0, 0, 0),
//                    "VietNam",
//                    "0328467924",
//                    4567,
//                    1155,
//                    11,
//                    gender = true, // true is male, false is female,
//                    isFirstSignIn = false // true if user the first sign in
//                )
//            )
//            add(
//                User(
//                    "a@gmail.com",
//                    "123456",
//                    R.drawable.logo,
//                    "Le Bui Tan Truong",
//                    "Truong HUIT",
//                    Date(2002, 1, 28, 0, 0, 0),
//                    "VietNam",
//                    "0328467924",
//                    4567,
//                    1155,
//                    11,
//                    gender = true, // true is male, false is female,
//                    isFirstSignIn = false // true if user the first sign in
//                )
//            )
//        }
//    }

    companion object {
        fun data(): ArrayList<User> {
            val data = ArrayList<User>()
            data.apply {
                add(
                    User(
                        "phuongviet.huit@gmail.com",
                        "Awdssdfg852456",
                        "awdssdfgviethufi@gmail.com",
                        R.drawable.logo,
                        "Nguyen Phuong Viet",
                        "Viet Huit",
                        Date(2002, 12, 6, 0, 0, 0),
                        "VietNam",
                        "84 93 982 63 76",
                        1234,
                        1023,
                        13,
                        gender = true, //true is male, false is female
                        isFirstSignIn = false // true if user the first sign in
                    )
                )
                add(
                    User(
                        "lebuitantruong@gmail.com",
                        "lbtt2801",
                        "truongletanbui@gmail.com",
                        R.drawable.logo,
                        "Le Bui Tan Truong",
                        "Truong HUIT",
                        Date(2002, 1, 28, 0, 0, 0),
                        "VietNam",
                        "84 32 846 79 24",
                        4567,
                        1155,
                        11,
                        gender = true, // true is male, false is female,
                        isFirstSignIn = false // true if user the first sign in
                    )
                )
                add(
                    User(
                        "a@gmail.com",
                        "123456",
                        "b@gmail.com",
                        R.drawable.logo,
                        "Le Bui Tan Truong",
                        "Truong HUIT",
                        Date(2002, 1, 28, 0, 0, 0),
                        "VietNam",
                        "84 32 846 79 25",
                        4567,
                        1155,
                        11,
                        gender = true, // true is male, false is female,
                        isFirstSignIn = false // true if user the first sign in
                    )
                )
            }
            return data
        }
    }
}