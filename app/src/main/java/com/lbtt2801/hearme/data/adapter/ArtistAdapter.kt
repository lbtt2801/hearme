package com.lbtt2801.hearme.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.databinding.ViewFollowArtistsBinding
import com.lbtt2801.hearme.databinding.ViewHomeArtistBinding
import com.lbtt2801.hearme.databinding.ViewTopListArtistBinding
import com.lbtt2801.hearme.model.Artist
import com.lbtt2801.hearme.viewmodel.UserViewModel

class ArtistAdapter(
    private val dataArtists: ArrayList<Artist>,
    private val type: Int,
    private val userViewModel: UserViewModel
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val HOME = 0
        const val POPULAR_ARTISTS = 1
        const val SAVED = 2
        const val FOLLOW_ARTISTS = 3
        const val ARTISTS_LIST = 4
    }

    override fun getItemViewType(position: Int): Int {
        return when (type) {
            0 -> HOME
            1 -> POPULAR_ARTISTS
            3 -> FOLLOW_ARTISTS
            4 -> ARTISTS_LIST
            else -> SAVED
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HOME -> HomeViewHolder(parent)
//            TOPIC_LIST -> InterestViewHolder(parent)
//            SAVED -> SavedViewHolder(parent)
            FOLLOW_ARTISTS -> ArtistViewHolderFollowArtists(parent)
            ARTISTS_LIST -> TopArtistViewHolder(parent)
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HomeViewHolder -> holder.bind(dataArtists[position])
            is ArtistViewHolderFollowArtists -> holder.bind(dataArtists[position])
            is TopArtistViewHolder -> holder.bind(dataArtists[position])
        }
    }

    override fun getItemCount(): Int = dataArtists.size

    inner class HomeViewHolder private constructor(
        val binding: ViewHomeArtistBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        constructor(parent: ViewGroup) : this(
            ViewHomeArtistBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        fun bind(artist: Artist) {
            binding.artist = artist
        }
    }

    inner class ArtistViewHolderFollowArtists private constructor(
        val binding: ViewFollowArtistsBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        constructor(parent: ViewGroup) : this(
            ViewFollowArtistsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        fun bind(artist: Artist) {
            binding.artist = artist

            binding.toggleButtonFollow.setOnCheckedChangeListener() { compound, isChecked ->
                val activity = compound.context as MainActivity
                userViewModel.updateFollowingArtists(
                    activity.email,
                    dataArtists[absoluteAdapterPosition],
                    isChecked
                )
            }
        }
    }

    inner class TopArtistViewHolder private constructor(
        val binding: ViewTopListArtistBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
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