package com.lbtt2801.hearme.data

import com.lbtt2801.hearme.model.RecentSearch
import com.lbtt2801.hearme.model.TopicSearch

class TopicSearchData {
    companion object {
        fun data(): ArrayList<TopicSearch> {
            val data = ArrayList<TopicSearch>()
            data.apply {
                add(TopicSearch("Top", true))
                add(TopicSearch("Songs", false))
                add(TopicSearch("Artists",false))
                add(TopicSearch("Albums",false))
                add(TopicSearch("Podcasts",false))
                add(TopicSearch("Playlists",false))
                add(TopicSearch("Profiles",false))
            }
            return data
        }
    }
}