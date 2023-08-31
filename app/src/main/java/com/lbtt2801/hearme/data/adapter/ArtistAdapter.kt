package com.lbtt2801.hearme.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lbtt2801.hearme.databinding.ViewTrendingNowBinding
import com.lbtt2801.hearme.model.Artist
import com.lbtt2801.hearme.model.Music

class ArtistAdapter(private val dataArtists: ArrayList<Artist>, private val type: Int ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val HOME = 0
        const val POPULAR_ARTISTS = 1
        const val SAVED = 2
    }

    override fun getItemViewType(position: Int): Int {
        return when (type) {
            0 -> HOME
            1 -> POPULAR_ARTISTS
            else -> SAVED
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
//            is HomeViewHolder -> holder.bind()
        }
    }

    override fun getItemCount(): Int = dataArtists.size

    inner class HomeViewHolder private constructor(
        val binding: ViewTrendingNowBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        constructor(parent: ViewGroup) : this(
            ViewTrendingNowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        fun bind(music: Music) {
            binding.music = music
        }
    }

//    inner class InterestViewHolder private constructor(
//        val binding: ViewTopicInterestBinding
//    ) : RecyclerView.ViewHolder(binding.root) {
//
//        constructor(parent: ViewGroup) : this(
//            ViewTopicInterestBinding.inflate(
//                LayoutInflater.from(parent.context),
//                parent,
//                false
//            )
//        )
//
//        fun bind(topic: Topic) {
//            binding.topic = topic
//        }
//    }

}