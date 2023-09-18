package com.lbtt2801.hearme.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lbtt2801.hearme.data.PlaylistData
import com.lbtt2801.hearme.model.Playlist

class PlaylistViewModel: ViewModel() {
    private val _lstDataPlaylist = MutableLiveData<ArrayList<Playlist>>()
    val lstDataPlaylist: LiveData<ArrayList<Playlist>>
        get() = _lstDataPlaylist

    private lateinit var lst: ArrayList<Playlist>

    init {
        getListDataPlaylist()
    }

    private fun getListDataPlaylist() {
        lst = PlaylistData.dataPlaylist()
        _lstDataPlaylist.postValue(lst)
    }
}