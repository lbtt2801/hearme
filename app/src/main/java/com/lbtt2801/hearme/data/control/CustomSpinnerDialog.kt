package com.lbtt2801.hearme.data.control

import com.lbtt2801.hearme.R

data class CustomSpinnerDialog(val img: Int , val name: String)

object data {
    private val imgs = intArrayOf(
        R.drawable.ic_show,
        R.drawable.ic_hide
    )

    private val names = arrayOf(
        "Public",
        "Private"
    )

    var list: ArrayList<CustomSpinnerDialog>?= null
        get() {
            if (field != null)
                return field
            field = ArrayList()
            field!!.add(CustomSpinnerDialog(imgs[0], names[0]))
            field!!.add(CustomSpinnerDialog(imgs[1], names[1]))
            return field
        }
}