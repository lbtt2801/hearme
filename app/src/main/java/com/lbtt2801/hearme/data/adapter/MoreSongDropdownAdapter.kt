package com.lbtt2801.hearme.data.adapter

import android.content.Context
import com.lbtt2801.hearme.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.lbtt2801.hearme.databinding.ViewMoreSongBinding
import com.lbtt2801.hearme.model.MoreSong


class MoreSongDropdownAdapter(val context: Context, var dataSource: ArrayList<MoreSong>) :
    BaseAdapter() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View
        val vh: ItemHolder
        if (convertView == null) {
            view = inflater.inflate(R.layout.view_more_song, parent, false)
            vh = ItemHolder(view)
            view?.tag = vh
        } else {
            view = convertView
            vh = view.tag as ItemHolder
        }
        vh.name.text = dataSource[position].name
        vh.img.setBackgroundResource(dataSource[position].image)

        return view
    }

    override fun getItem(position: Int): Any? {
        return dataSource[position];
    }

    override fun getCount(): Int {
        return dataSource.size;
    }

    override fun getItemId(position: Int): Long {
        return position.toLong();
    }

    private class ItemHolder(view: View?) {
        val name: TextView
        val img: ImageView

        init {
            name = view?.findViewById(R.id.name_dropdown) as TextView
            img = view.findViewById(R.id.image_dropdown) as ImageView
        }
    }
}