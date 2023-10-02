package com.lbtt2801.hearme.view.fragments.search

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.media3.common.MediaItem
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.data.ArtistsData
import com.lbtt2801.hearme.data.CategoriesData
import com.lbtt2801.hearme.databinding.FragmentNowSongPlayingBinding
import com.lbtt2801.hearme.model.Music
import com.lbtt2801.hearme.model.Time
import com.lbtt2801.hearme.viewmodel.SongPlayViewModel
import java.util.Date
import com.bumptech.glide.request.RequestOptions
import com.lbtt2801.hearme.viewmodel.MusicViewModel


class NowSongPlayingFragment : Fragment() {
    lateinit var binding: FragmentNowSongPlayingBinding
    private lateinit var mainActivity: MainActivity
    private var music: Music? = null
    private var lstDataMusic = ArrayList<Music>()
    private val musicViewModel: MusicViewModel by activityViewModels()
    private val songPlayViewModel: SongPlayViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_now_song_playing, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        mainActivity = (activity as MainActivity)

        musicViewModel.lstDataMusics.observe(viewLifecycleOwner) {
            lstDataMusic = it
        }

        binding.music = Music(
            "ms001",
            "Shades of Love",
            0,
            "https://firebasestorage.googleapis.com/v0/b/hearme-app-16567.appspot.com/o/images%2Fmusics%2Fwithout_you.png?alt=media&token=e78ac2d2-7e0e-4373-84f1-5f2d3b7ef445&_gl=1*1gfxh8m*_ga*MTUyOTk3NDI1NS4xNjkzMzU5NjY4*_ga_CW55HF8NVT*MTY5NTg4NjQxNS43LjEuMTY5NTg4ODExOS40MC4wLjA.",
            Time(0, 4, 30),
            Date("02/5/2023"),
            CategoriesData.dataCategory().first { it.categoryID == "ca001" },
            ArtistsData.dataArtist().first { it.artistId == "ar001" },
            true
        )
        songPlayViewModel.selectedItem.observe(requireActivity()) {
            if (it != null) {
                binding.music = it
                music = it
            }

            setImageMusic(it.image)
            binding.tvTitle.text = it.musicName.plus(" - ")
                .plus(it.artist.artistName)
            binding.btnPlay.isChecked = it.isPlaying!!

            if (!binding.btnPlay.isChecked)
                mainActivity.binding.fragmentBottomPlayer.isVisible = false
        }

        binding.btnPlay.setOnClickListener {
            if (!binding.btnPlay.isChecked)
                mainActivity.binding.fragmentBottomPlayer.isVisible = true
        }

        binding.btnNext.setOnClickListener {
            var positionSong = lstDataMusic.indexOf(music)
            if (positionSong == lstDataMusic.size - 1)
                return@setOnClickListener
            positionSong += 1
            Log.v(TAG, "L1: Name: ${music?.musicName} - Play: ${music?.isPlaying}")
            music?.isPlaying = false
            Log.v(TAG, "L2: Name: ${music?.musicName} - Play: ${music?.isPlaying}")
            music = musicViewModel.lstDataMusics.value?.first { it.musicID == lstDataMusic[positionSong].musicID }
            music?.isPlaying = true
            music?.image?.let { it1 -> setImageMusic(it1) }
            Log.v(TAG, "L3: Name: ${music?.musicName} - Play: ${music?.isPlaying}")
            binding.tvTitle.text = music?.musicName.plus(" - ").plus(music?.artist?.artistName)
            binding.music = music
            mainActivity.exoPlayer.setMediaItem(MediaItem.fromUri(music?.path!!))
            mainActivity.exoPlayer.prepare()
            mainActivity.exoPlayer.play()
        }

    }

    private fun setImageMusic(strUrl: String) {
        val options = RequestOptions()
            .centerCrop()
            .error(R.drawable.ellipse)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)
            .dontTransform()

        Glide.with(this)
            .load(strUrl)
            .apply(options)
            .transition(DrawableTransitionOptions.withCrossFade(250))
            .into(binding.imgAvatar)
    }
}