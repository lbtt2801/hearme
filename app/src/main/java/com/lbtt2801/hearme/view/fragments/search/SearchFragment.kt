package com.lbtt2801.hearme.view.fragments.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.data.adapter.RecentSearchesAdapter
import com.lbtt2801.hearme.databinding.FragmentSearchBinding

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
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        mainActivity = activity as MainActivity

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
            false
        )
    }
}