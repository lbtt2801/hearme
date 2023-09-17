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
import com.lbtt2801.hearme.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val activity: MainActivity = (activity as MainActivity)
        activity.customToolbar(
            "VISIBLE",
            "History",
            null,
            R.color.transparent,
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back),
            showIcMore = true,
            showIcFilter = false,
            showIcSearch = true
        )
        activity.showBottomNav("GONE")
        (activity as MainActivity).binding.toolBar.setNavigationOnClickListener() {
            findNavController().popBackStack()
        }

        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabLayout = binding.tabLayout
        val viewPage2 = binding.viewPager2

        val viewPageAdapter = ViewPageAdapter(childFragmentManager, lifecycle)
        viewPage2.adapter = viewPageAdapter

        TabLayoutMediator(tabLayout, viewPage2) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Songs"
                }

                1 -> tab.text = "Podcasts"
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}