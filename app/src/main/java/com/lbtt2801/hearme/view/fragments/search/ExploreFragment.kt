package com.lbtt2801.hearme.view.fragments.search

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
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
import androidx.recyclerview.widget.RecyclerView
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.data.adapter.*
import com.lbtt2801.hearme.databinding.ContainerSearchResultBinding
import com.lbtt2801.hearme.databinding.FragmentExploreBinding
import com.lbtt2801.hearme.model.*
import com.lbtt2801.hearme.viewmodel.*

class ExploreFragment : Fragment(),
    RecentSearchesAdapter.RecentSearchAdapterCallBack {
    private lateinit var binding: FragmentExploreBinding
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
    private lateinit var userAdapter: UserAdapter

    private lateinit var includeTopsSongsArtistsAlbumsPlaylistsProfiles: ContainerSearchResultBinding

    private var saveInstanceTextSearch: String = ""
    private var isScrollingUp = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            saveInstanceTextSearch = savedInstanceState.getString("textSearch").toString()
            Log.v("saveInstanceTextSearch", "$savedInstanceState")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_explore, container, false)
        includeTopsSongsArtistsAlbumsPlaylistsProfiles =
            binding.includeTopsSongsArtistsAlbumsPlaylistsProfiles
        return binding.root
    }

    private fun displayRecyclerViewTopicSearch(data: ArrayList<TopicSearch>) {
        includeTopsSongsArtistsAlbumsPlaylistsProfiles.recyclerViewTopicSearch.apply {
            layoutManager =
                LinearLayoutManager(view?.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = TopicSearchAdapter(data, 0)
        }
    }

    override fun onResume() {
        super.onResume()
        mainActivity = (activity as MainActivity)

//        val a = includeTopsSongsArtistsAlbumsPlaylistsProfiles.recyclerViewTopicSearch
//        includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearch.recyclerViewFoundList.addOnScrollListener(
//            object : RecyclerView.OnScrollListener() {
//                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                    super.onScrolled(recyclerView, dx, dy)
//                    if (dy > 0) {
//                        if (isScrollingUp) {
////                            includeTopsSongsArtistsAlbumsPlaylistsProfiles.containsRecyclerViewTopicSearch.startAnimation(
////                                AnimationUtils.loadAnimation(context, R.anim.anim_upward))
//                            a.animate()
//                                .alpha(0.0f)
//                                .setListener(object : AnimatorListenerAdapter() {
//                                    override fun onAnimationEnd(animation: Animator) {
//                                        super.onAnimationEnd(animation);
//                                        a.visibility = View.GONE;
//                                    }
//                                })
//                            isScrollingUp = false
//                        }
//                    } else {
//                        if (!isScrollingUp) {
////                            includeTopsSongsArtistsAlbumsPlaylistsProfiles.containsRecyclerViewTopicSearch.startAnimation(
////                                AnimationUtils.loadAnimation(context, R.anim.anim_downward))
//                            a.animate()
//                                .alpha(1.0f)
//                                .setListener(object : AnimatorListenerAdapter() {
//                                    override fun onAnimationEnd(animation: Animator) {
//                                        super.onAnimationEnd(animation);
//                                        a.visibility = View.VISIBLE;
//                                    }
//                                })
//
//                            isScrollingUp = true
//                        }
//                    }
//                }
//            })

        if (saveInstanceTextSearch.isNotEmpty()) {
            binding.containerBrowseAll.visibility = View.GONE
            binding.containerRecentSearches.visibility = View.GONE
            includeTopsSongsArtistsAlbumsPlaylistsProfiles.root.visibility = View.VISIBLE
            mainActivity.showBottomNav("GONE")
            mainActivity.customToolbar("GONE")
            binding.searchView.setQuery(saveInstanceTextSearch, true)
        }

        recentSearchViewModel.lstDataRecentSearch.observe(mainActivity) {
            displayRecyclerViewRecentSearches(it)
        }

        viewModel.lstDataCategory.observe((activity as MainActivity), Observer {
            displayRecyclerView(it as ArrayList<Category>)
        })
        viewModel.getListDataArtist()

        initSearchBar()

        binding.textClearAll.setOnClickListener() {
            recentSearchViewModel.deleteAll()
        }

        mainActivity.customToolbar(
            "VISIBLE",
            "Explore",
            null,
            R.color.transparent,
            ContextCompat.getDrawable(requireContext(), R.drawable.logo_nav),
            true
        )
        mainActivity.showBottomNav("VISIBLE")

        if (binding.searchView.query.isNotEmpty()) {
            binding.searchView.setQuery("${binding.searchView.query}", true)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("textSearch", saveInstanceTextSearch)
        Log.v(TAG, "onSaveInstanceState saveInstanceTextSearch -> $saveInstanceTextSearch")
    }

    private fun displayRecyclerView(lstData: ArrayList<Category>) {
        val layoutRecyclerView = if (mainActivity.isLandscape()) GridLayoutManager(view?.context,
            3, LinearLayoutManager.VERTICAL, false)
        else
            GridLayoutManager(view?.context, 2, LinearLayoutManager.VERTICAL, false)
        categoryAdapter = CategoryAdapter(lstData, 0) {
            if (it.getInt("position") == 1)
                findNavController().navigate(R.id.podcastFragment, it)
            else if (it.getInt("position") == 2)
                findNavController().navigate(R.id.itemExploreFragment, it)
            else
                findNavController().navigate(R.id.itemExploreFragment, it)
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
                topicSearchViewModel.getTopicSearch().value?.let { displayRecyclerViewTopicSearch(it) }

                binding.containerRecentSearches.visibility = View.GONE
                includeTopsSongsArtistsAlbumsPlaylistsProfiles.root.visibility = View.VISIBLE
                mainActivity.showBottomNav("GONE")
                mainActivity.customToolbar("GONE")

                if (p0 != null) {
                    recentSearchViewModel.updateDataRecentSearch(p0)
                    topicSearchViewModel.getTopicSearch().observe(requireActivity()) { data ->
                        includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeNotfoundSearch.root.visibility =
                            View.GONE
                        includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearch.root.visibility =
                            View.GONE
                        includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchPodcasts.root.visibility =
                            View.GONE
                        when (data.first { it.isChecked }.name) {
                            "Top" -> {
                                if (displayRecyclerFoundTopList(p0)) {
                                    includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearch.root.visibility =
                                        View.VISIBLE
                                } else {
                                    includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeNotfoundSearch.root.visibility =
                                        View.VISIBLE
                                }
                            }
                            "Songs" -> {
                                if (displayRecyclerFoundSongsList(p0)) {
                                    includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearch.root.visibility =
                                        View.VISIBLE
                                } else {
                                    includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeNotfoundSearch.root.visibility =
                                        View.VISIBLE
                                }
                            }
                            "Artists" -> {
                                if (displayRecyclerFoundArtistsList(p0)) {
                                    includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearch.root.visibility =
                                        View.VISIBLE
                                } else {
                                    includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeNotfoundSearch.root.visibility =
                                        View.VISIBLE
                                }
                            }
                            "Albums" -> {
                                if (displayRecyclerFoundAlbumsList(p0)) {
                                    includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearch.root.visibility =
                                        View.VISIBLE
                                } else {
                                    includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeNotfoundSearch.root.visibility =
                                        View.VISIBLE
                                }
                            }
                            "Podcasts" -> {
                                if (displayRecyclerFoundPodcastsList(p0)) {
                                    includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchPodcasts.root.visibility =
                                        View.VISIBLE
                                } else {
                                    includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeNotfoundSearch.root.visibility =
                                        View.VISIBLE
                                }
                            }
                            "Playlists" -> {
                                displayRecyclerFoundPlaylistsList(p0)
                            }
                            "Profiles" -> {
                                if (displayRecyclerFoundProfilesList(p0)) {
                                    includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearch.root.visibility =
                                        View.VISIBLE
                                } else {
                                    includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeNotfoundSearch.root.visibility =
                                        View.VISIBLE
                                }
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
                saveInstanceTextSearch = p0.toString()
                return true
            }
        })
    }

    private fun displayRecyclerFoundProfilesList(p0: String): Boolean {
        val listFoundUser: ArrayList<User> = userViewModel.lstDataUser.value?.filter {
            it.email != mainActivity.email &&
                    it.fullName.trim().lowercase()
                        .contains(p0.trim().lowercase())
        } as ArrayList<User>

        if (listFoundUser.isEmpty()) {
            return false
        }

        userAdapter = UserAdapter(listFoundUser, 0, this)
        includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearch.recyclerViewFoundList.apply {
            layoutManager = LinearLayoutManager(view?.context, LinearLayoutManager.VERTICAL, false)
            adapter = userAdapter
        }

        return true
    }

    private fun displayRecyclerFoundPlaylistsList(p0: String): Boolean {
        return false

    }

    private fun displayRecyclerFoundPodcastsList(p0: String): Boolean {
        //Search artist is podcast
        val listFoundArtist: ArrayList<Artist> =
            artistViewModel.lstDataArtists.value?.filter {
                !it.isSinger && it.artistName.trim().lowercase().contains(p0.trim().lowercase())
            } as ArrayList<Artist>

        //Search podcast
        val listFoundPodcast: ArrayList<Music> =
            musicViewModel.lstDataMusics.value?.filter {
                it.category.categoryID == "ca002" && it.musicName.trim().lowercase()
                    .contains(p0.trim().lowercase())
            } as ArrayList<Music>

        if (listFoundArtist.isEmpty() && listFoundPodcast.isEmpty()) {
            return false
        }

        //Search podcast of artist above
        val listPodcastOfArtist: ArrayList<Music> = ArrayList()
        listFoundArtist.forEach { list ->
            listPodcastOfArtist.addAll(musicViewModel.lstDataMusics.value?.filter {
                it.category.categoryID == "ca002" && it.artist.artistId == list.artistId
            } as ArrayList<Music>)
        }

        artistAdapter = ArtistAdapter(listFoundArtist, 5)
        includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchPodcasts.recyclerViewPodcastAndShow.apply {
            layoutManager =
                GridLayoutManager(view?.context, 1, LinearLayoutManager.HORIZONTAL, false)
            adapter = artistAdapter
        }

        musicAdapter = if (listPodcastOfArtist.isNotEmpty()) {
            MusicAdapter(
                listFoundPodcast.union(listPodcastOfArtist).toList() as ArrayList<Music>,
                2, this
            )
        } else {
            MusicAdapter(listFoundPodcast, 2, this)
        }

        includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchPodcasts.recyclerViewEpisode.apply {
            layoutManager =
                LinearLayoutManager(view?.context, LinearLayoutManager.VERTICAL, false)
            adapter = musicAdapter
        }

        return true
    }

    private fun displayRecyclerFoundAlbumsList(p0: String): Boolean {
        val listFoundMusic: ArrayList<Music> = musicViewModel.lstDataMusics.value?.filter {
            it.musicName.trim().lowercase().contains(p0.trim().lowercase()) && it.isAlbum
        } as ArrayList<Music>

        if (listFoundMusic.isEmpty()) {
            return false
        }

        musicAdapter =
            MusicAdapter(
                listFoundMusic,
                4, this
            )


        val layout = if (mainActivity.isLandscape())
            GridLayoutManager(view?.context, 3, LinearLayoutManager.VERTICAL, false)
        else
            GridLayoutManager(view?.context, 2, LinearLayoutManager.VERTICAL, false)

        includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearch.recyclerViewFoundList.apply {
            layoutManager = layout
            adapter = musicAdapter
        }

        return true
    }

    private fun displayRecyclerFoundArtistsList(p0: String): Boolean {
        val listFoundArtist: ArrayList<Artist> =
            artistViewModel.lstDataArtists.value?.filter {
                it.artistName.trim().lowercase().contains(p0.trim().lowercase())
            } as ArrayList<Artist>

        if (listFoundArtist.isEmpty())
            return false

        artistAdapter =
            ArtistAdapter(
                listFoundArtist,
                4,
            )

        includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearch.recyclerViewFoundList.apply {
            layoutManager = LinearLayoutManager(view?.context, LinearLayoutManager.VERTICAL, false)
            adapter = artistAdapter
        }

        return true
    }

    private fun displayRecyclerFoundSongsList(p0: String): Boolean {
        val listFoundMusic: ArrayList<Music> =
            musicViewModel.lstDataMusics.value?.filter {
                it.musicName.trim().lowercase()
                    .contains(p0.trim()
                        .lowercase()) && it.category.categoryID != "ca002" && !it.isAlbum
            } as ArrayList<Music>

        if (listFoundMusic.isEmpty())
            return false

        musicAdapter =
            MusicAdapter(
                listFoundMusic,
                6, this
            )

        includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearch.recyclerViewFoundList.apply {
            layoutManager = LinearLayoutManager(view?.context, LinearLayoutManager.VERTICAL, false)
            adapter = musicAdapter
        }

        return true
    }

    private fun displayRecyclerFoundTopList(p0: String): Boolean {
        val listFoundMusic: ArrayList<Music> =
            musicViewModel.lstDataMusics.value?.sortedByDescending { it.totalListeners }?.filter {
                it.musicName.trim().lowercase()
                    .contains(p0.trim().lowercase()) && it.category.categoryID != "ca002"
            } as ArrayList<Music>
        val listFoundArtist: ArrayList<Artist> =
            artistViewModel.lstDataArtists.value?.sortedByDescending { it.totalNumberOfListeners }
                ?.filter {
                    it.artistName.trim().lowercase().contains(p0.trim().lowercase())
                } as ArrayList<Artist>

        if (listFoundArtist.isEmpty() && listFoundMusic.isEmpty())
            return false

        musicAdapter =
            MusicAdapter(
                listFoundMusic,
                3, this
            )
        artistAdapter =
            ArtistAdapter(
                listFoundArtist,
                4
            )
        val concatAdapter = ConcatAdapter(musicAdapter, artistAdapter)

        includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearch.recyclerViewFoundList.apply {
            layoutManager = LinearLayoutManager(view?.context, LinearLayoutManager.VERTICAL, false)
            adapter = concatAdapter
        }

        return true
    }

    private fun displayRecyclerViewRecentSearches(lstData: ArrayList<RecentSearch>) {
        val layout =
            LinearLayoutManager(view?.context, LinearLayoutManager.VERTICAL, false)
        recentSearchesAdapter =
            RecentSearchesAdapter(lstData)
        recentSearchesAdapter.setCallBack(this)
        binding.recyclerViewRecentSearches.apply {
            layoutManager = layout
            adapter = recentSearchesAdapter
        }
    }

    override fun setQuery(name: String) {
        binding.searchView.setQuery(name, true)
    }
}