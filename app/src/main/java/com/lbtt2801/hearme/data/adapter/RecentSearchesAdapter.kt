package com.lbtt2801.hearme.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lbtt2801.hearme.databinding.ViewRecentSearchBinding
import com.lbtt2801.hearme.model.RecentSearch

class RecentSearchesAdapter(private val dataSearches: ArrayList<RecentSearch>) :
    RecyclerView.Adapter<RecentSearchesAdapter.SearchViewHolder>() {
    private lateinit var binding: ViewRecentSearchBinding

    inner class SearchViewHolder(binding: ViewRecentSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recentSearch: RecentSearch) {
            binding.recentSearch = recentSearch
//            binding.imageViewClearResultSearch.setOnClickListener {
//                val position = absoluteAdapterPosition
//                remove(
//                    it,
//                    position
//                )
//                notifyItemRemoved(position)
//            }
//            binding.textViewResultSearch.setOnClickListener() {
//                val position = absoluteAdapterPosition
//                val mainActivity = it.context as MainActivity
//                mainActivity.binding.searchView.setQuery(dataSearches[position].name.toString(), true)
//            }
        }

//        private fun remove(it: View?, position: Int) {
//            if (position >= 0)
//                dataSearches.removeAt(position)
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        binding =
            ViewRecentSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(dataSearches[position])
    }

    override fun getItemCount() = dataSearches.size
}