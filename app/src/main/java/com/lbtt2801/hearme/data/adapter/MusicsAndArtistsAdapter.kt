package com.lbtt2801.hearme.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lbtt2801.hearme.databinding.*
import com.lbtt2801.hearme.model.Artist
import com.lbtt2801.hearme.model.Music

class MusicsAndArtistsAdapter(
    private val dataMusics: ArrayList<Music>,
    private val dataArtists: ArrayList<Artist>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val VIEW_TYPE_MUSIC = 1
        const val VIEW_TYPE_ARTIST = 2
    }

    inner class View1ViewHolder private constructor(
        val binding: ViewTopListMusicBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        constructor(parent: ViewGroup) : this(
            ViewTopListMusicBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        fun bind(music: Music) {
            binding.music = music
        }
    }

    inner class View2ViewHolder private constructor(
        val binding: ViewTopListArtistBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        constructor(parent: ViewGroup) : this(
            ViewTopListArtistBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        fun bind(artist: Artist) {
            binding.artist = artist
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_MUSIC -> View1ViewHolder(parent)
            VIEW_TYPE_ARTIST -> View2ViewHolder(parent)
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is View1ViewHolder -> holder.bind(dataMusics[position])
            is View2ViewHolder -> holder.bind(dataArtists[position])
        }
    }

    override fun getItemCount() = dataMusics.size + dataArtists.size
}