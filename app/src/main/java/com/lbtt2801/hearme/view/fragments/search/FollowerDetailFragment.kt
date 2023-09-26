package com.lbtt2801.hearme.view.fragments.search

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
import com.lbtt2801.hearme.data.adapter.ViewPageFollowAdapter
import com.lbtt2801.hearme.databinding.FragmentFollowerDetailBinding

class FollowerDetailFragment : Fragment() {
    private var _binding: FragmentFollowerDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainActivity: MainActivity

    private var email: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_follower_detail, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        mainActivity = (activity as MainActivity)
        email = arguments?.getString("emailID")


        mainActivity.customToolbar(
            "VISIBLE",
            "",
            null,
            R.color.transparent,
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back),
            showIcMore = true,
            showIcFilter = false,
            showIcSearch = true
        )
        mainActivity.showBottomNav("GONE")
        mainActivity.binding.toolBar.setNavigationOnClickListener() {
            findNavController().popBackStack()
        }

        val tabLayout = binding.tabLayout
        val viewPage2 = binding.viewPager2

        val viewPageFollowAdapter =
            email?.let { ViewPageFollowAdapter(childFragmentManager, lifecycle, it) }
        viewPage2.adapter = viewPageFollowAdapter

        TabLayoutMediator(tabLayout, viewPage2) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Followers"
                }

                1 -> tab.text = "Following"
            }
        }.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}