package com.lbtt2801.hearme.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lbtt2801.hearme.databinding.ViewHomeTrendingBinding
import com.lbtt2801.hearme.databinding.ViewListSongBinding
import com.lbtt2801.hearme.model.Music

class MusicAdapter(private val dataMusics: ArrayList<Music>, private val type: Int): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val HOME = 0
        const val SONG_LIST = 1
        const val SAVED = 2
    }

    override fun getItemViewType(position: Int): Int {
        return when (type) {
            0 -> HOME
            1 -> SONG_LIST
            else -> SAVED
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HOME -> HomeViewHolder(parent)
            SONG_LIST -> SongNotificationViewHolder(parent)
//            SAVED -> SavedViewHolder(parent)
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int = dataMusics.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HomeViewHolder -> holder.bind(dataMusics[position])
            is SongNotificationViewHolder -> holder.bind(dataMusics[position])
        }
    }

    inner class HomeViewHolder private constructor(
        val binding: ViewHomeTrendingBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        constructor(parent: ViewGroup) : this(
            ViewHomeTrendingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        fun bind(music: Music) {
            binding.music = music
        }
    }

    inner class SongNotificationViewHolder private constructor(
        val binding: ViewListSongBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        constructor(parent: ViewGroup) : this(
            ViewListSongBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        fun bind(music: Music) {
            binding.music = music
        }
    }
}