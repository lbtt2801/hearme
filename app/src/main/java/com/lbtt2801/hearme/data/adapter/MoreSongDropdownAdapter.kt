package com.lbtt2801.hearme.data.adapter

import android.annotation.SuppressLint
import com.lbtt2801.hearme.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.lbtt2801.hearme.model.MoreSong


class MoreSongDropdownAdapter(val context: Context, var data: ArrayList<MoreSong>) :
    BaseAdapter() {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context)
            .inflate(R.layout.view_more_song, parent, false)

        val txtName = view.findViewById<TextView>(R.id.name_dropdown)
        val image = view.findViewById<ImageView>(R.id.image_dropdown)
        val container = view.findViewById<LinearLayout>(R.id.container_item_dropdown)

        txtName.text = data[position].name
        image.setImageResource(data[position].image)



        if (position == 0) {
            txtName.visibility = View.GONE
            image.visibility = View.GONE
            view.visibility = View.GONE
            container.visibility = View.GONE
        }
        return view
    }

    override fun getItem(position: Int): Any? {
        return data[position];
    }

    override fun getCount(): Int {
        return data.size;
    }

    override fun getItemId(position: Int): Long {
        return position.toLong();
    }
}