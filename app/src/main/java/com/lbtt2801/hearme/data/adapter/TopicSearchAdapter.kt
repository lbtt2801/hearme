package com.lbtt2801.hearme.data.adapter

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ToggleButton
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.model.TopicSearch
import com.lbtt2801.hearme.view.search.ExploreFragment
import com.lbtt2801.hearme.viewmodel.TopicSearchViewModel
import kotlin.math.acos

class TopicSearchAdapter(private val data: ArrayList<TopicSearch>) :
    RecyclerView.Adapter<TopicSearchAdapter.ViewHolder>() {
    private var rowIndex = -1
    private var first = true

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val toggleButton: ToggleButton = itemView.findViewById(R.id.toggle_buttn_topic)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_topic_search, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mainActivity = holder.toggleButton.context as MainActivity

        val itemsViewModel = data[position]
        holder.toggleButton.text = itemsViewModel.name
        holder.toggleButton.textOff = itemsViewModel.name
        holder.toggleButton.textOn = itemsViewModel.name

        holder.toggleButton.setOnClickListener() {
            for (i in 0 until data.size) {
                update(i, false)
                mainActivity.viewModelTopicSearch.updateChecked(i, false)
            }
            rowIndex = position
            update(rowIndex, true)
            mainActivity.viewModelTopicSearch.updateChecked(rowIndex, true)
        }

        holder.toggleButton.isChecked = rowIndex == position

        if (first) {
            holder.toggleButton.isChecked = itemsViewModel.isChecked
            first = false
        }
    }

    private fun update(position: Int, boolean: Boolean) {
        data[position].isChecked = boolean
        notifyDataSetChanged()
    }

    override fun getItemCount() = data.size
}