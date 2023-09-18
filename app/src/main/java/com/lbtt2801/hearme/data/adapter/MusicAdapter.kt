package com.lbtt2801.hearme.data.adapter

import android.app.ActionBar.LayoutParams
import android.app.AlertDialog
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.LinearLayout
import android.widget.Spinner
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.data.MoreSongData
import com.lbtt2801.hearme.data.control.CustomSpinner
import com.lbtt2801.hearme.databinding.*
import com.lbtt2801.hearme.model.MoreSong
import com.lbtt2801.hearme.model.Music
import kotlin.math.roundToInt


class MusicAdapter(private val dataMusics: ArrayList<Music>, private val type: Int) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val HOME = 0
        const val SONG_LIST = 1
        const val PODCAST_LIST = 2
        const val MUSIC_LIST = 3
        const val ALBUM_LIST = 4
        const val LIBRARY_SONG_LIST = 5
    }

    override fun getItemViewType(position: Int): Int {
        return when (type) {
            0 -> HOME
            1 -> SONG_LIST
            2 -> PODCAST_LIST
            3 -> MUSIC_LIST
            4 -> ALBUM_LIST
            5 -> LIBRARY_SONG_LIST
            else -> MUSIC_LIST
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HOME -> HomeViewHolder(parent)
            SONG_LIST -> SongNotificationViewHolder(parent)
            PODCAST_LIST -> PodcastNotificationViewHolder(parent)
            MUSIC_LIST -> TopMusicViewHolder(parent)
            ALBUM_LIST -> AlbumViewHolder(parent)
            LIBRARY_SONG_LIST -> LibraryListSongViewHolder(parent)
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int = dataMusics.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HomeViewHolder -> holder.bind(dataMusics[position])
            is SongNotificationViewHolder -> holder.bind(dataMusics[position])
            is PodcastNotificationViewHolder -> holder.bind(dataMusics[position])
            is LibraryListSongViewHolder -> holder.bind(dataMusics[position])
            is TopMusicViewHolder -> {
                holder.bind(dataMusics[position])
                holder.itemView.setOnClickListener() {
                    it.findNavController()
                        .navigate(R.id.action_item_nav_explore_to_viewDetailsSongFragment,
                            Bundle().apply {
                                putString("musicID", dataMusics[position].musicID)
                            }
                        )
                }
            }
            is AlbumViewHolder -> holder.bind(dataMusics[position])
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

            val mainActivity = binding.spinnerDropDownMore.context as MainActivity
            mainActivity.initSpinnerMore(
                binding.spinnerDropDownMore,
                dataMusics[absoluteAdapterPosition],
                0
            )
        }
    }

    inner class PodcastNotificationViewHolder private constructor(
        val binding: ViewListPodcastBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        constructor(parent: ViewGroup) : this(
            ViewListPodcastBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        fun bind(music: Music) {
            binding.music = music

            binding.spinnerDropDownMore.adapter =
                MoreSongDropdownAdapter(binding.spinnerDropDownMore.context, MoreSongData.data(), 1)

            binding.spinnerDropDownMore.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        v: View?,
                        position: Int,
                        id: Long
                    ) {
                        val music = dataMusics[absoluteAdapterPosition]
                        val mainActivity = v?.context as MainActivity

                        when (position) {
                            3 -> { // Add to blacklist
                                val isDontPlay: Boolean
                                if (mainActivity.viewModelUser.lstDataUser.value?.first { it.email == mainActivity.email }?.blackListMusic?.none { it.musicID == music.musicID } == true) {
                                    isDontPlay = true
                                    mainActivity.showSnack(
                                        v,
                                        "You added ${music.musicName} to blacklist!"
                                    )
                                } else {
                                    isDontPlay = false
                                    mainActivity.showSnack(
                                        v,
                                        "You removed ${music.musicName} from blacklist!"
                                    )
                                }
                                music.let {
                                    mainActivity.viewModelUser.updateBlackListMusic(
                                        mainActivity.email,
                                        it,
                                        isDontPlay
                                    )
                                }
                            }
                            5 -> { // View artist

                            }
                            6 -> { // Go to album

                            }
                            7 -> { // Share

                            }
                        }
                    }
                }
        }
    }

    inner class TopMusicViewHolder private constructor(
        val binding: ViewTopListMusicBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        constructor(parent: ViewGroup) : this(
            ViewTopListMusicBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        fun bind(music: Music) {
            binding.music = music

            val mainActivity = binding.spinnerDropDownMore.context as MainActivity
            mainActivity.initSpinnerMore(
                binding.spinnerDropDownMore,
                dataMusics[absoluteAdapterPosition],
                0
            )
        }
    }

    inner class AlbumViewHolder private constructor(
        val binding: ViewAlbumBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        constructor(parent: ViewGroup) : this(
            ViewAlbumBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        fun bind(music: Music) {
            binding.music = music

            if (absoluteAdapterPosition % 2 == 0) {
                val paramsContainer = LinearLayout.LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT
                ).apply {
                    marginEnd = (12 * Resources.getSystem().displayMetrics.density).roundToInt()
                }
                binding.containerView.layoutParams = paramsContainer
            }
        }
    }

    inner class LibraryListSongViewHolder private constructor(
        val binding: ViewListSongLibraryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        constructor(parent: ViewGroup) : this(
            ViewListSongLibraryBinding.inflate(
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