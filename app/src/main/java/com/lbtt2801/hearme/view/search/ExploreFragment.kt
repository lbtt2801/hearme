package com.lbtt2801.hearme.view.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.data.adapter.CategoryAdapter
import com.lbtt2801.hearme.data.adapter.RecentSearchesAdapter
import com.lbtt2801.hearme.data.adapter.TopicSearchAdapter
import com.lbtt2801.hearme.databinding.FragmentExploreBinding
import com.lbtt2801.hearme.model.Category
import com.lbtt2801.hearme.model.RecentSearch
import com.lbtt2801.hearme.model.TopicSearch
import com.lbtt2801.hearme.viewmodel.HomeViewModel
import com.lbtt2801.hearme.viewmodel.RecentSearchViewModel

class ExploreFragment : Fragment() {
    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainActivity: MainActivity
    private lateinit var categoryAdapter: CategoryAdapter
    private val viewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    private val recentSearchViewModel: RecentSearchViewModel by activityViewModels()

    private lateinit var recentSearchesAdapter: RecentSearchesAdapter
    private val arrayListTopicSearch = arrayListOf<TopicSearch>(
        TopicSearch("Top"),
        TopicSearch("Songs"),
        TopicSearch("Artists"),
        TopicSearch("Albums"),
        TopicSearch("Artists"),
        TopicSearch("Podcasts"),
        TopicSearch("Playlists"),
        TopicSearch("Profiles"),
    )

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
        binding.recyclerViewTopicSearch.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = TopicSearchAdapter(arrayListTopicSearch)
        }

        recentSearchViewModel.lstDataRecentSearch.observe(mainActivity) {
            displayRecyclerViewRecentSearches(it)
        }

        viewModel.lstDataCategory.observe((activity as MainActivity), Observer {
            displayRecyclerView(it as ArrayList<Category>)
        })
        viewModel.getListDataArtist()

        initSearchBar()
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
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                binding.containerBrowseAll.visibility = View.GONE
                binding.containerRecentSearches.visibility = View.VISIBLE
                mainActivity.showBottomNav("GONE")

                return true
            }
        })
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