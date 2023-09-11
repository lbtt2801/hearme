package com.lbtt2801.hearme.view.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.data.RecentSearchesData
import com.lbtt2801.hearme.data.adapter.MusicAdapter
import com.lbtt2801.hearme.data.adapter.RecentSearchesAdapter
import com.lbtt2801.hearme.databinding.FragmentSearchBinding
import com.lbtt2801.hearme.model.Music
import com.lbtt2801.hearme.model.RecentSearch

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var mainActivity: MainActivity

    private lateinit var recentSearchesAdapter: RecentSearchesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_search, container, false
        )
        mainActivity = activity as MainActivity
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        mainActivity.showBottomNav("GONE")
        mainActivity.customToolbar(
            "visible",
            null,
            null,
            R.color.transparent,
            null,
            false,
            false,
            false,
            false,
            true
        )
        displayRecyclerViewRecentSearches(RecentSearchesData.data())
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