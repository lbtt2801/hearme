package com.lbtt2801.hearme.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lbtt2801.hearme.databinding.ViewTopicSearchBinding
import com.lbtt2801.hearme.model.TopicSearch

class TopicSearchAdapter(private val data: ArrayList<TopicSearch>) :
    RecyclerView.Adapter<TopicSearchAdapter.UserHolder>() {
    private lateinit var binding: ViewTopicSearchBinding
    private var oldSelected = 0

    inner class UserHolder(binding: ViewTopicSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(topicSearch: TopicSearch) {
            binding.topicSearch = topicSearch

//            binding.checkBox.setOnCheckedChangeListener() { _, check ->
//                paint(
//                    check,
//                    binding.root
//                )
//            }
//            binding.root.setOnLongClickListener() { remove(it) }
        }
//
//        private fun paint(boolean: Boolean, it: View?) {
//            val i = adapterPosition
//            dataUser[i].isActive = boolean
//        }

//        private fun remove(it: View): Boolean {
//            var i = adapterPosition
//            dataUser.removeAt(i)
//            return true
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        binding = ViewTopicSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserHolder(binding)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size
}