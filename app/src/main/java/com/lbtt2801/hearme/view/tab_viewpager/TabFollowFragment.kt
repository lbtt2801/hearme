package com.lbtt2801.hearme.view.tab_viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.data.adapter.UserAdapter
import com.lbtt2801.hearme.databinding.FragmentTabFollowBinding
import com.lbtt2801.hearme.model.User
import com.lbtt2801.hearme.viewmodel.UserViewModel

class TabFollowFragment(page: Int) : Fragment() {
    private lateinit var binding: FragmentTabFollowBinding
    private lateinit var mainActivity: MainActivity
    private lateinit var userAdapter: UserAdapter
    private var lstFollowers: ArrayList<User> = ArrayList()
    private var lstFollowing: ArrayList<User> = ArrayList()
    private var email: String? = ""
    private val getPage = page
    private val userViewModel: UserViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tab_follow, container, false)

        mainActivity = (activity as MainActivity)
        email = mainActivity.email

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        userViewModel.lstDataUser.observe(viewLifecycleOwner, Observer {
            lstFollowers = it.first { user -> user.email == email }.listFollowers
            lstFollowing = it.first { user -> user.email == email }.listUserFollowing
        })
        if (getPage == 0)
            displayRecyclerView(lstFollowers)
        if (getPage == 1)
            displayRecyclerView(lstFollowing)
    }

    private fun displayRecyclerView(lstData: ArrayList<User>) {
        val layoutRecyclerViewMusic =
            LinearLayoutManager(view?.context, LinearLayoutManager.VERTICAL, false)
        userAdapter = UserAdapter(lstData, 0)
        binding.recyclerView.apply {
            layoutManager = layoutRecyclerViewMusic
            adapter = userAdapter
        }
    }
}