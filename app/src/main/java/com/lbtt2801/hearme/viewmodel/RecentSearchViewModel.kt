package com.lbtt2801.hearme.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lbtt2801.hearme.data.RecentSearchesData
import com.lbtt2801.hearme.model.RecentSearch

class RecentSearchViewModel : ViewModel() {
    private val _lstDataRecentSearch = MutableLiveData<ArrayList<RecentSearch>>()
    val lstDataRecentSearch: LiveData<ArrayList<RecentSearch>>
        get() = _lstDataRecentSearch

    private lateinit var lst: ArrayList<RecentSearch>

    fun getListDataRecentSearches() {
        lst = RecentSearchesData.data()
        _lstDataRecentSearch.postValue(lst)
    }
}