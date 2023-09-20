package com.lbtt2801.hearme.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.data.PlaylistData
import com.lbtt2801.hearme.model.Music
import com.lbtt2801.hearme.model.Playlist
import com.lbtt2801.hearme.model.User

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

//    fun addDataPlaylist(id: String, name: String, img: Int, lstMusic: ArrayList<Music>) {
//        lst.add( Playlist( id, name, img, lstMusic ) )
//        _lstDataPlaylist.postValue(lst)
//    }
}