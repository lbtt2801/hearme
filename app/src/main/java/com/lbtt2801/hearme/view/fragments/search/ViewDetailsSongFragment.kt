package com.lbtt2801.hearme.view.fragments.search

import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.data.MoreSongData
import com.lbtt2801.hearme.data.adapter.MoreSongDropdownAdapter
import com.lbtt2801.hearme.data.adapter.MusicAdapter
import com.lbtt2801.hearme.databinding.FragmentViewDetailsSongBinding
import com.lbtt2801.hearme.model.Artist
import com.lbtt2801.hearme.model.Music
import com.lbtt2801.hearme.model.User
import com.lbtt2801.hearme.viewmodel.MusicViewModel
import com.lbtt2801.hearme.viewmodel.UserViewModel

class ViewDetailsSongFragment : Fragment() {
    private lateinit var binding: FragmentViewDetailsSongBinding
    private lateinit var mainActivity: MainActivity
    private val musicViewModel: MusicViewModel by activityViewModels()
    private val userViewModel: UserViewModel by activityViewModels()

    private lateinit var musicID: String
    private var artist: Artist? = null
    var music: Music? = null
    private var musicsMoreLikeThis: ArrayList<Music>? = arrayListOf()
    private var user: User? = null

    private lateinit var musicAdapter: MusicAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_view_details_song, container, false
        )
        mainActivity = activity as MainActivity
        musicID = arguments?.getString("musicID").toString()
        artist = musicViewModel.lstDataMusics.value?.first { it.musicID == musicID }?.artist
        music = musicViewModel.lstDataMusics.value?.first { it.musicID == musicID }
        musicsMoreLikeThis =
            musicViewModel.lstDataMusics.value?.filter { it.artist.artistId == artist?.artistId && it.musicID != musicID } as ArrayList<Music>?
        user = userViewModel.lstDataUser.value?.first { it.email == mainActivity.email }
        binding.music = music
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageArtist.setImageDrawable(
            artist?.image?.let {
                ContextCompat.getDrawable(
                    view.context,
                    it
                )
            }
        )
        binding.textViewMusicName.text = music?.musicName
        binding.textViewArtistName.text = artist?.artistName
        binding.textViewCategory.text = if (music?.isAlbum == true) "Album" else "Song"
        val duration = "${music?.duration?.minute}:${music?.duration?.second}"
        binding.textViewDuration.text = duration

        music?.let { mainActivity.initSpinnerMore(binding.spinnerDropDownMore, it, 1, this) }

        displayRecyclerViewMoreLikeThis()
    }

    private fun displayRecyclerViewMoreLikeThis() {
        musicAdapter = musicsMoreLikeThis?.let { MusicAdapter(it, 6, this) }!!
        binding.recyclerViewMusicMoreLikeThis.apply {
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
            adapter = musicAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        mainActivity.showBottomNav("GONE")
        mainActivity.customToolbar(
            "VISIBLE",
            navIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back),
            showIcMore = true
        )
        mainActivity.binding.toolBar.setNavigationOnClickListener() {
            findNavController().popBackStack()
        }
    }
}