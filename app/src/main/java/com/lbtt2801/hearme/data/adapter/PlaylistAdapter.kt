package com.lbtt2801.hearme.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lbtt2801.hearme.databinding.ViewListPlaylistBinding
import com.lbtt2801.hearme.model.Playlist

class PlaylistAdapter (private val dataplaylist: ArrayList<Playlist>, private val type: Int ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val LIBRARY = 0
    }

    override fun getItemViewType(position: Int): Int {
        return when (type) {
            0 -> LIBRARY
            else -> LIBRARY
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            LIBRARY -> LibraryViewHolder(parent)
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is LibraryViewHolder -> holder.bind(dataplaylist[position])
        }
    }

    override fun getItemCount(): Int = dataplaylist.size

    inner class LibraryViewHolder private constructor(
        val binding: ViewListPlaylistBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        constructor(parent: ViewGroup) : this(
            ViewListPlaylistBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        fun bind(playlist: Playlist) {
            binding.playlist = playlist
        }
    }
}