package com.lbtt2801.hearme.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.data.adapter.ArtistAdapter
import com.lbtt2801.hearme.data.adapter.MusicAdapter
import com.lbtt2801.hearme.databinding.FragmentPopularArtistsBinding
import com.lbtt2801.hearme.model.Artist
import com.lbtt2801.hearme.model.Music
import com.lbtt2801.hearme.viewmodel.HomeViewModel

class PopularArtistsFragment : Fragment() {
    private var _binding: FragmentPopularArtistsBinding?= null
    private val binding get() = _binding!!
    private lateinit var artistAdapter: ArtistAdapter

    private val viewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_popular_artists, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).customToolbar("VISIBLE","Popular Artists",R.color.transparent,R.drawable.ic_arrow_back,
            showIcMore = false,
            showIcSearch = true
        )

        viewModel.lstDataArtist.observe((activity as MainActivity), Observer {
            displayRecyclerView(it as ArrayList<Artist>)
        })
        viewModel.getListDataArtist()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun displayRecyclerView(lstData: ArrayList<Artist>) {
        val layoutRecyclerView = GridLayoutManager(view?.context, 2, LinearLayoutManager.VERTICAL, false)
        artistAdapter = ArtistAdapter(lstData, 0)
        binding.recyclerView.apply {
            layoutManager = layoutRecyclerView
            adapter = artistAdapter
        }
    }
}