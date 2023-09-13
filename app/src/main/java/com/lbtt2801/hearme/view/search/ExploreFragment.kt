package com.lbtt2801.hearme.view.search

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.data.ArtistsData
import com.lbtt2801.hearme.data.adapter.*
import com.lbtt2801.hearme.databinding.FragmentExploreBinding
import com.lbtt2801.hearme.model.*
import com.lbtt2801.hearme.viewmodel.*

class ExploreFragment : Fragment() {
    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainActivity: MainActivity
    private lateinit var categoryAdapter: CategoryAdapter
    private val viewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    private val recentSearchViewModel: RecentSearchViewModel by activityViewModels()
    private val topicSearchViewModel: TopicSearchViewModel by activityViewModels()
    private val artistViewModel: ArtistViewModel by activityViewModels()
    private val musicViewModel: MusicViewModel by activityViewModels()
    private val userViewModel: UserViewModel by activityViewModels()

    private lateinit var recentSearchesAdapter: RecentSearchesAdapter
    private lateinit var musicAdapter: MusicAdapter
    private lateinit var artistAdapter: ArtistAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_explore, container, false)
        mainActivity = (activity as MainActivity)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayRecyclerViewTopicSearch(topicSearchViewModel.lstDataTopicSearch.value as ArrayList<TopicSearch>)

        recentSearchViewModel.lstDataRecentSearch.observe(mainActivity) {
            displayRecyclerViewRecentSearches(it)
        }

        viewModel.lstDataCategory.observe((activity as MainActivity), Observer {
            displayRecyclerView(it as ArrayList<Category>)
        })
        viewModel.getListDataArtist()

        initSearchBar()
    }

    private fun displayRecyclerViewTopicSearch(data: ArrayList<TopicSearch>) {
        binding.recyclerViewTopicSearch.apply {
            layoutManager =
                LinearLayoutManager(view?.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = TopicSearchAdapter(data)
        }
    }

    override fun onResume() {
        super.onResume()
        mainActivity.customToolbar(
            "VISIBLE",
            "Explore",
            null,
            R.color.transparent,
            ContextCompat.getDrawable(requireContext(), R.drawable.logo_nav),
            true
        )
        mainActivity.showBottomNav("VISIBLE")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun displayRecyclerView(lstData: ArrayList<Category>) {
        val layoutRecyclerView =
            GridLayoutManager(view?.context, 2, LinearLayoutManager.VERTICAL, false)
        categoryAdapter = CategoryAdapter(lstData, 0) {
            if (it.getInt("position") == 1)
                findNavController().navigate(R.id.podcastFragment, it)
            else if (it.getInt("position") == 2)
                findNavController().navigate(R.id.itemExploreFragment, it)
            else findNavController().navigate(R.id.itemExploreFragment, it)
        }
        binding.recyclerView.apply {
            layoutManager = layoutRecyclerView
            adapter = categoryAdapter
        }
    }

    private fun initSearchBar() {
        binding.searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                binding.containerRecentSearches.visibility = View.GONE
                binding.containerSearchResults.visibility = View.VISIBLE
                mainActivity.showBottomNav("VISIBLE")
                mainActivity.customToolbar("GONE")
                if (p0 != null) {
                    topicSearchViewModel.lstDataTopicSearch.observe(requireActivity()) { data ->
                        when (data.first { it.isChecked }.name) {
                            "Top" -> {
                                if (displayRecyclerFoundTopList(p0)) {
                                    binding.containerNotFound.visibility = View.GONE
                                    binding.containerFound.visibility = View.VISIBLE
                                } else {
                                    binding.containerNotFound.visibility = View.VISIBLE
                                    binding.containerFound.visibility = View.GONE
                                }
                            }
                            "Songs" -> {
                                if (displayRecyclerFoundSongsList(p0)) {
                                    binding.containerNotFound.visibility = View.GONE
                                    binding.containerFound.visibility = View.VISIBLE
                                } else {
                                    binding.containerNotFound.visibility = View.VISIBLE
                                    binding.containerFound.visibility = View.GONE
                                }
                            }
                            "Artists" -> {
                                if (displayRecyclerFoundArtistsList(p0)) {
                                    binding.containerNotFound.visibility = View.GONE
                                    binding.containerFound.visibility = View.VISIBLE
                                } else {
                                    binding.containerNotFound.visibility = View.VISIBLE
                                    binding.containerFound.visibility = View.GONE
                                }
                            }
                            "Albums" -> {
                                if (displayRecyclerFoundAlbumsList(p0)) {
                                    binding.containerNotFound.visibility = View.GONE
                                    binding.containerFound.visibility = View.VISIBLE
                                } else {
                                    binding.containerNotFound.visibility = View.VISIBLE
                                    binding.containerFound.visibility = View.GONE
                                }
                            }
                            "Podcasts" -> {
                                displayRecyclerFoundPodcastsList(p0)
                            }
                            "Playlists" -> {
                                displayRecyclerFoundPlaylistsList(p0)
                            }
                            "Profiles" -> {
                                displayRecyclerFoundProfilesList(p0)
                            }
                        }
                    }
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                binding.containerBrowseAll.visibility = View.GONE
                binding.containerRecentSearches.visibility = View.VISIBLE
                mainActivity.showBottomNav("GONE")
                mainActivity.customToolbar("GONE")

                return true
            }
        })
    }

    private fun displayRecyclerFoundProfilesList(p0: String): Boolean {
        return false
    }

    private fun displayRecyclerFoundPlaylistsList(p0: String): Boolean {
        return false

    }

    private fun displayRecyclerFoundPodcastsList(p0: String): Boolean {
        return false

    }

    private fun displayRecyclerFoundAlbumsList(p0: String): Boolean {
        val listFoundMusic =
            musicViewModel.lstDataMusics.value?.filter {
                it.musicName.trim().lowercase().contains(p0.trim().lowercase()) && it.isAlbum
            }

        musicAdapter =
            MusicAdapter(
                listFoundMusic as ArrayList<Music>,
                4
            )

        binding.recyclerViewFoundList.apply {
            layoutManager = GridLayoutManager(view?.context, 2, LinearLayoutManager.VERTICAL, false)
            adapter = musicAdapter
        }

        if (listFoundMusic.isNotEmpty())
            return true
        return false
    }

    private fun displayRecyclerFoundArtistsList(p0: String): Boolean {
        val listFoundArtist =
            artistViewModel.lstDataArtists.value?.filter {
                it.artistName.trim().lowercase().contains(p0.trim().lowercase())
            }

        artistAdapter =
            ArtistAdapter(
                listFoundArtist as ArrayList<Artist>,
                4,
                userViewModel
            )

        binding.recyclerViewFoundList.apply {
            layoutManager = LinearLayoutManager(view?.context, LinearLayoutManager.VERTICAL, false)
            adapter = artistAdapter
        }

        if (listFoundArtist.isNotEmpty())
            return true
        return false
    }

    private fun displayRecyclerFoundSongsList(p0: String): Boolean {
        val listFoundMusic =
            musicViewModel.lstDataMusics.value?.filter {
                it.musicName.trim().lowercase().contains(p0.trim().lowercase())
            }

        musicAdapter =
            MusicAdapter(
                listFoundMusic as ArrayList<Music>,
                3
            )

        binding.recyclerViewFoundList.apply {
            layoutManager = LinearLayoutManager(view?.context, LinearLayoutManager.VERTICAL, false)
            adapter = musicAdapter
        }

        if (listFoundMusic.isNotEmpty())
            return true
        return false
    }

    private fun displayRecyclerFoundTopList(p0: String): Boolean {
        val listFoundMusic =
            musicViewModel.lstDataMusics.value?.sortedByDescending { it.totalListeners }?.filter {
                it.musicName.trim().lowercase().contains(p0.trim().lowercase())
            }
        val listFoundArtist =
            artistViewModel.lstDataArtists.value?.sortedByDescending { it.totalNumberOfListeners }
                ?.filter {
                    it.artistName.trim().lowercase().contains(p0.trim().lowercase())
                }

        musicAdapter =
            MusicAdapter(
                listFoundMusic as ArrayList<Music>,
                3
            )
        artistAdapter =
            ArtistAdapter(
                listFoundArtist as ArrayList<Artist>,
                4,
                userViewModel
            )
        val concatAdapter = ConcatAdapter(musicAdapter, artistAdapter)

        binding.recyclerViewFoundList.apply {
            layoutManager = LinearLayoutManager(view?.context, LinearLayoutManager.VERTICAL, false)
            adapter = concatAdapter
        }

        if (listFoundArtist.isNotEmpty() || listFoundMusic.isNotEmpty())
            return true
        return false
    }

    private fun displayRecyclerViewRecentSearches(lstData: ArrayList<RecentSearch>) {
        val layout =
            LinearLayoutManager(view?.context, LinearLayoutManager.VERTICAL, false)
        recentSearchesAdapter =
            RecentSearchesAdapter(lstData)
        binding.recyclerViewRecentSearches.apply {
            layoutManager = layout
            adapter = recentSearchesAdapter
        }
    }
}