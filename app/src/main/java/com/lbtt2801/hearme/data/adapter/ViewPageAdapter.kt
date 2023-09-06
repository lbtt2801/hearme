package com.lbtt2801.hearme.data.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.lbtt2801.hearme.view.NotificationFragment
import com.lbtt2801.hearme.view.SignInFragment
import com.lbtt2801.hearme.view.SignUpFragment
import com.lbtt2801.hearme.view.SongNotificationFragment

class ViewPageAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return  when (position) {
            0 -> { SongNotificationFragment() }
            1 -> { SignUpFragment() }
            else -> { NotificationFragment() }
        }
    }
}