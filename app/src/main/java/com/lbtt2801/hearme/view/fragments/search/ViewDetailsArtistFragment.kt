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
import androidx.recyclerview.widget.LinearLayoutManager
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.data.adapter.MusicAdapter
import com.lbtt2801.hearme.databinding.FragmentViewDetailsArtistBinding
import com.lbtt2801.hearme.databinding.FragmentViewDetailsSongBinding
import com.lbtt2801.hearme.model.Artist
import com.lbtt2801.hearme.model.Music
import com.lbtt2801.hearme.model.User
import com.lbtt2801.hearme.viewmodel.ArtistViewModel
import com.lbtt2801.hearme.viewmodel.MusicViewModel
import com.lbtt2801.hearme.viewmodel.UserViewModel

class ViewDetailsArtistFragment : Fragment() {
    private lateinit var binding: FragmentViewDetailsArtistBinding
    private lateinit var mainActivity: MainActivity
    private val artistViewModel: ArtistViewModel by activityViewModels()
    private val musicViewModel: MusicViewModel by activityViewModels()

    private lateinit var artistID: String
    private var artist: Artist? = null
    private var dataSongs: ArrayList<Music> = ArrayList()

    private lateinit var musicAdapter: MusicAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_view_details_artist, container, false
        )
        mainActivity = activity as MainActivity
        artistID = arguments?.getString("artistID").toString()
        artist = artistViewModel.lstDataArtists.value?.first { it.artistId == artistID }!!
        dataSongs =
            musicViewModel.lstDataMusics.value?.filter { it.artist.artistId == artistID && !it.isAlbum } as ArrayList<Music>
        binding.artist = artist
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayRecyclerViewPopularSongs()
    }

    private fun displayRecyclerViewPopularSongs() {
        musicAdapter = MusicAdapter(dataSongs,6,this)
        binding.recyclerViewPopularSongs.apply {
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