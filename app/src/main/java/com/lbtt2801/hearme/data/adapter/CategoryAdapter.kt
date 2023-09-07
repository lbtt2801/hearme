package com.lbtt2801.hearme.data.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lbtt2801.hearme.databinding.ViewListBrowseBinding
import com.lbtt2801.hearme.model.Category

class CategoryAdapter (
    private val dataCategory: ArrayList<Category>,
    private val type: Int,
    val onItemClick: ((Bundle) -> Unit)? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val EXPLORE = 0
        const val POPULAR_ARTISTS = 1
        const val SAVED = 2
    }

    override fun getItemViewType(position: Int): Int {
        return when (type) {
            0 -> EXPLORE
            1 -> POPULAR_ARTISTS
            else -> SAVED
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            EXPLORE -> ExploreViewHolder(parent)
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ExploreViewHolder -> {
                holder.bind(dataCategory[position])
                holder.itemView.setOnClickListener {
                    val param = Bundle().apply {
                        putString("name", dataCategory[position].categoryName)
                        putInt("position", position)
                    }
                    onItemClick?.invoke(param)
                }
            }
        }
    }

    override fun getItemCount(): Int = dataCategory.size

    inner class ExploreViewHolder private constructor(
        val binding: ViewListBrowseBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        constructor(parent: ViewGroup) : this(
            ViewListBrowseBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        fun bind(category: Category) {
            binding.category = category
        }
    }
}