package com.lbtt2801.hearme.data.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.databinding.ViewItemPlaylistBinding
import com.lbtt2801.hearme.databinding.ViewListPlaylistBinding
import com.lbtt2801.hearme.databinding.ViewPlaylistBinding
import com.lbtt2801.hearme.model.Playlist
import com.lbtt2801.hearme.view.fragments.library.PlaylistDetailFragment

class PlaylistAdapter(
    private val dataplaylist: ArrayList<Playlist>,
    private val type: Int,
    val onItemClick: ((Bundle) -> Unit)? = null,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val LIBRARY = 0
        const val PROFILE_DETAIL = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (type) {
            0 -> LIBRARY
            1 -> PROFILE_DETAIL
            else -> LIBRARY
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            LIBRARY -> LibraryViewHolder(parent)
            PROFILE_DETAIL -> ProfileDetailViewHolder(parent)
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is LibraryViewHolder -> {
                holder.bind(dataplaylist[position])
                holder.itemView.setOnClickListener {
                    val param = Bundle().apply {
                        putInt("img", dataplaylist[position].image)
                        putString("id", dataplaylist[position].playlistID)
                        putString("name", dataplaylist[position].playlistName)
                        putString("size", dataplaylist[position].size)
//                        putInt("position", position)
                    }
                    onItemClick?.invoke(param)
                }
            }
            is ProfileDetailViewHolder -> {
                holder.bind(dataplaylist[position])
            }
        }
    }

    override fun getItemCount(): Int = dataplaylist.size

    inner class LibraryViewHolder private constructor(
        val binding: ViewListPlaylistBinding,
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

    inner class ProfileDetailViewHolder private constructor(
        val binding: ViewItemPlaylistBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        constructor(parent: ViewGroup) : this(
            ViewItemPlaylistBinding.inflate(
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