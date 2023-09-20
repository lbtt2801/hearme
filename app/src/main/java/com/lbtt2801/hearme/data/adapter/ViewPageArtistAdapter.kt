package com.lbtt2801.hearme.data.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.lbtt2801.hearme.view.fragments.onboardingsignupsignin.SplashScreenFragment
import com.lbtt2801.hearme.view.tab_viewpager.TabPodcastersFragment
import com.lbtt2801.hearme.view.tab_viewpager.TabSingerFragment

class ViewPageArtistAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return  when (position) {
            0 -> { TabSingerFragment() }
            1 -> { TabPodcastersFragment() }
            else -> { SplashScreenFragment() }
        }
    }
}