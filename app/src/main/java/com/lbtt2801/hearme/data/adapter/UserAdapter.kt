package com.lbtt2801.hearme.data.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.databinding.ViewProfileBinding
import com.lbtt2801.hearme.model.User

class UserAdapter(
    private val dataUsers: ArrayList<User>,
    private val type: Int,
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
        var destination: Int? = null
        when (holder) {
            is SearchProfileViewHolder -> {
                holder.bind(dataUsers[position])
                destination = R.id.action_item_nav_explore_to_viewDetailsProfileFragment
            }
        }
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
        val binding: ViewProfileBinding
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