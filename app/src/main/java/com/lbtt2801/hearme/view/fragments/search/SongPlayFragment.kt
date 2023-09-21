package com.lbtt2801.hearme.view.fragments.search

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.databinding.FragmentSongPlayBinding
import kotlinx.coroutines.delay

class SongPlayFragment : Fragment() {
    private lateinit var binding: FragmentSongPlayBinding
    private lateinit var mainActivity: MainActivity
    private lateinit var runnable: Runnable
    private var handler = Handler()
    private var mediaPlayer = MediaPlayer()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_song_play, container, false)
        mainActivity = (activity as MainActivity)

        mainActivity.customToolbar(
            "VISIBLE",
            "",
            null,
            R.color.transparent,
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back), true
        )
        mainActivity.showBottomNav("GONE")
        mainActivity.binding.toolBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        mediaPlayer = MediaPlayer.create(context, R.raw.shape_of_you_nokia)
        binding.seekBar.progress = 0
        binding.seekBar.max = mediaPlayer.duration

        mediaPlayer.start()
        binding.btnPlay.setImageResource(R.drawable.ic_pause_80)

        binding.seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, position: Int, changed: Boolean) {
                if (changed) {
                    mediaPlayer.seekTo(position)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })

        runnable = Runnable {
            binding.seekBar.progress = mediaPlayer.currentPosition
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)

        mediaPlayer.setOnCompletionListener {
            binding.btnPlay.setImageResource(R.drawable.ic_play_80)
            binding.seekBar.progress = 0
        }


        binding.linearCache.setOnClickListener {
            binding.linearCache.isVisible = false
            binding.linearLyrics.isVisible = true
        }

        binding.btnPlay.setOnClickListener {
            if (!mediaPlayer.isPlaying) {
                mediaPlayer.start()
                binding.btnPlay.setImageResource(R.drawable.ic_pause_80)
            } else {
                mediaPlayer.pause()
                binding.btnPlay.setImageResource(R.drawable.ic_play_80)
            }
        }
    }
}