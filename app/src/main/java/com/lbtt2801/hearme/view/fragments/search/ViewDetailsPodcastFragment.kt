package com.lbtt2801.hearme.view.fragments.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.data.adapter.MusicAdapter
import com.lbtt2801.hearme.databinding.FragmentViewDetailsPodcastBinding
import com.lbtt2801.hearme.databinding.FragmentViewDetailsSongBinding
import com.lbtt2801.hearme.model.Artist
import com.lbtt2801.hearme.model.Music
import com.lbtt2801.hearme.model.User
import com.lbtt2801.hearme.viewmodel.MusicViewModel
import com.lbtt2801.hearme.viewmodel.UserViewModel

class ViewDetailsPodcastFragment : Fragment() {
    private lateinit var binding: FragmentViewDetailsPodcastBinding
    private lateinit var mainActivity: MainActivity
    private val musicViewModel: MusicViewModel by activityViewModels()
    private val userViewModel: UserViewModel by activityViewModels()

    private lateinit var musicID: String
    private var artist: Artist? = null
    private var music: Music? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_view_details_podcast, container, false
        )
        mainActivity = activity as MainActivity
        musicID = arguments?.getString("musicID").toString()
        artist = musicViewModel.lstDataMusics.value?.first { it.musicID == musicID }?.artist
        music = musicViewModel.lstDataMusics.value?.first { it.musicID == musicID }
        binding.music = music
        binding.artist = artist
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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