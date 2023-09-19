package com.lbtt2801.hearme.view.fragments.library

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.data.adapter.ViewPageAdapter
import com.lbtt2801.hearme.data.adapter.ViewPagePodcastAdapter
import com.lbtt2801.hearme.databinding.FragmentPodcastLibraryBinding
import com.lbtt2801.hearme.model.Music

class PodcastLibraryFragment : Fragment() {
    private var _binding: FragmentPodcastLibraryBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_podcast_library, container, false)

        mainActivity = (activity as MainActivity)

        mainActivity.customToolbar(
            "VISIBLE",
            "Podcasts",
            null,
            R.color.transparent,
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back),
            showIcMore = true,
            showIcFilter = false,
            showIcSearch = true
        )
        mainActivity.showBottomNav("GONE")
        mainActivity.binding.toolBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        val tabLayout = binding.tabLayout
        val viewPage2 = binding.viewPager2

        val viewPagePodcastFragment = ViewPagePodcastAdapter(childFragmentManager, lifecycle)
        viewPage2.adapter = viewPagePodcastFragment

        TabLayoutMediator(tabLayout, viewPage2) { tab, position ->
            when (position) {
                0 -> tab.text = "Your Likes"
                1 -> tab.text = "Queue"
                2 -> tab.text = "Downloaded"
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}