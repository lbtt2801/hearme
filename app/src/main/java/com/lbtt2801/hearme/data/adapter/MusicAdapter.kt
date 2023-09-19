package com.lbtt2801.hearme.data.adapter

import android.app.ActionBar.LayoutParams
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.data.MoreSongData
import com.lbtt2801.hearme.databinding.*
import com.lbtt2801.hearme.model.Music
import com.lbtt2801.hearme.view.fragments.homeactionmenu.HomeFragment
import com.lbtt2801.hearme.view.fragments.homeactionmenu.NotificationFragment
import com.lbtt2801.hearme.view.fragments.homeactionmenu.TrendingNowFragment
import com.lbtt2801.hearme.view.fragments.search.ExploreFragment
import com.lbtt2801.hearme.view.fragments.search.ViewDetailsSongFragment
import kotlin.math.roundToInt


class MusicAdapter(
    private val dataMusics: ArrayList<Music>,
    private val type: Int,
    private val fragment: Fragment
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val HOME = 0
        const val SONG_LIST = 1
        const val PODCAST_LIST = 2
        const val MUSIC_LIST = 3
        const val ALBUM_LIST = 4
        const val LIBRARY_SONG_LIST = 5
        const val SONG_LIST_2 = 6
    }

    override fun getItemViewType(position: Int): Int {
        return when (type) {
            0 -> HOME
            1 -> SONG_LIST
            2 -> PODCAST_LIST
            3 -> MUSIC_LIST
            4 -> ALBUM_LIST
            5 -> LIBRARY_SONG_LIST
            6 -> SONG_LIST_2
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
            SONG_LIST_2 -> SongList2ViewHolder(parent)
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int = dataMusics.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var destination: Int? = null
        when (holder) {
            is HomeViewHolder -> {
                holder.bind(dataMusics[position])
                if (fragment is HomeFragment)
                    destination = R.id.action_item_nav_home_to_viewDetailsSongFragment
                else if (fragment is TrendingNowFragment)
                    destination = R.id.action_trendingNowFragment_to_viewDetailsSongFragment
            }
            is SongNotificationViewHolder -> {
                holder.bind(dataMusics[position])
                destination = R.id.action_notificationFragment_to_viewDetailsSongFragment
            }
            is PodcastNotificationViewHolder -> {
                holder.bind(dataMusics[position])
            }
            is LibraryListSongViewHolder -> {
                holder.bind(dataMusics[position])
            }
            is TopMusicViewHolder -> {
                holder.bind(dataMusics[position])
                destination = R.id.action_item_nav_explore_to_viewDetailsSongFragment
            }
            is AlbumViewHolder -> {
                holder.bind(dataMusics[position])
            }
            is SongList2ViewHolder -> {
                holder.bind(dataMusics[position])
                if (fragment is ExploreFragment)
                    destination = R.id.action_item_nav_explore_to_viewDetailsSongFragment
                else if (fragment is ViewDetailsSongFragment) {
                    destination = R.id.action_viewDetailsSongFragment_to_viewDetailsArtistFragment
                }
            }
        }
        holder.itemView.setOnClickListener() {
            if (destination != null) {
                it.findNavController()
                    .navigate(destination,
                        Bundle().apply {
                            putString("musicID", dataMusics[position].musicID)
                        }
                    )
            }
        }
    }

    inner class SongList2ViewHolder private constructor(
        val binding: ViewListSong2Binding
    ) : RecyclerView.ViewHolder(binding.root) {
        constructor(parent: ViewGroup) : this(
            ViewListSong2Binding.inflate(
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
                0,
                fragment
            )
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
                0,
                fragment
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
                                val isDown: Boolean
                                if (mainActivity.viewModelUser.lstDataUser.value?.first { it.email == mainActivity.email }?.listMusicsDownloaded?.none { it.musicID == music.musicID } == true) {
                                    isDown = true
                                    mainActivity.showSnack(
                                        v,
                                        "You added ${music.musicName} to List Downloaded!"
                                    )
                                } else {
                                    isDown = false
                                    mainActivity.showSnack(
                                        v,
                                        "You removed ${music.musicName} from List Downloaded!"
                                    )
                                }
                                mainActivity.viewModelUser.updateListMusicsDownloaded(
                                    mainActivity.email,
                                    music,
                                    isDown
                                )
                            }
                        }
                    }
                }
//            val notificationFragment = NotificationFragment()
//            val mainActivity = binding.spinnerDropDownMore.context as MainActivity
//            mainActivity.initSpinnerMore(
//                binding.spinnerDropDownMore,
//                dataMusics[absoluteAdapterPosition],
//                1,
//                notificationFragment
//            )
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
                0,
                fragment
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

            val mainActivity = binding.spinnerDropDownMore.context as MainActivity
            mainActivity.initSpinnerMore(
                binding.spinnerDropDownMore,
                dataMusics[absoluteAdapterPosition],
                0,
                fragment
            )
        }
    }
}