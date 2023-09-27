package com.lbtt2801.hearme.data.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.databinding.ViewProfileBinding
import com.lbtt2801.hearme.model.User
import com.lbtt2801.hearme.view.fragments.search.ExploreFragment
import com.lbtt2801.hearme.view.tab_viewpager.TabFollowFragment
import com.lbtt2801.hearme.view.tab_viewpager.TabSongFragment

class UserAdapter(
    private val dataUsers: ArrayList<User>,
    private val type: Int,
    private val fragment: Fragment,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val SEARCH_PROFILES = 0
    }

    override fun getItemViewType(position: Int): Int {
        return when (type) {
            0 -> SEARCH_PROFILES
            else -> SEARCH_PROFILES
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            SEARCH_PROFILES -> SearchProfileViewHolder(parent)
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val mainActivity = fragment.context as MainActivity
        var destination: Int? = null
        when (holder) {
            is SearchProfileViewHolder -> {
                holder.bind(dataUsers[position])
                if (fragment is ExploreFragment)
                    destination = R.id.action_item_nav_explore_to_viewDetailsProfileFragment
                else if (fragment is TabFollowFragment) {
                    destination = R.id.viewDetailsProfileFragment
                }
            }
        }
        if (dataUsers[position].email != mainActivity.email)
            holder.itemView.setOnClickListener() {
                if (destination != null) {
                    it.findNavController()
                        .navigate(destination,
                            Bundle().apply {
                                putString("userEmail", dataUsers[position].email)
                            }
                        )
                }
            }
    }

    override fun getItemCount(): Int = dataUsers.size

    inner class SearchProfileViewHolder private constructor(
        val binding: ViewProfileBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        constructor(parent: ViewGroup) : this(
            ViewProfileBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        fun bind(user: User) {
            binding.user = user
        }
    }
}