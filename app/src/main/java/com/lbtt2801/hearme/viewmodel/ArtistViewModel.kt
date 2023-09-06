package com.lbtt2801.hearme.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lbtt2801.hearme.data.FakeData
import com.lbtt2801.hearme.data.UserData
import com.lbtt2801.hearme.model.Artist
import com.lbtt2801.hearme.model.User
import java.util.*
import kotlin.collections.ArrayList

class ArtistViewModel : ViewModel() {
    private val _lstDataArtists = MutableLiveData<ArrayList<Artist>>()
    val lstDataArtists: LiveData<ArrayList<Artist>>
        get() = _lstDataArtists

    private lateinit var lst: ArrayList<Artist>

    fun getListDataArtists() {
        lst = FakeData.dataArtist()
        _lstDataArtists.postValue(lst)
    }
}