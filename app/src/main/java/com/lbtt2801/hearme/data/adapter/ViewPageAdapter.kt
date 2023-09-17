package com.lbtt2801.hearme.data.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.lbtt2801.hearme.view.fragments.homeactionmenu.NotificationFragment
import com.lbtt2801.hearme.view.fragments.homeactionmenu.TabPodcastFragment
import com.lbtt2801.hearme.view.fragments.homeactionmenu.TabSongFragment
import com.lbtt2801.hearme.view.fragments.library.HistoryFragment
import com.lbtt2801.hearme.view.fragments.onboardingsignupsignin.SplashScreenFragment

class ViewPageAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return  when (position) {
            0 -> { TabSongFragment() }
            1 -> { TabPodcastFragment() }
            else -> { SplashScreenFragment() }
        }
    }
}