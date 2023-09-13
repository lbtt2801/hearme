package com.lbtt2801.hearme.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lbtt2801.hearme.data.TopicSearchData
import com.lbtt2801.hearme.model.TopicSearch

class TopicSearchViewModel : ViewModel() {
    private val _lstDataTopicSearch = MutableLiveData<ArrayList<TopicSearch>>()
    val lstDataTopicSearch: LiveData<ArrayList<TopicSearch>>
        get() = _lstDataTopicSearch

    private lateinit var lst: ArrayList<TopicSearch>

    fun getListDataTopicSearch() {
        lst = TopicSearchData.data()
        _lstDataTopicSearch.postValue(lst)
    }

    fun updateChecked(position: Int, boolean: Boolean) {
        lst[position].isChecked = boolean
        _lstDataTopicSearch.postValue(lst)
    }
}