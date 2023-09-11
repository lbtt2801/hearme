package com.lbtt2801.hearme.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lbtt2801.hearme.data.MusicsData
import com.lbtt2801.hearme.model.Music

class MusicViewModel : ViewModel() {
    private val _lstDataMusics = MutableLiveData<ArrayList<Music>>()
    val lstDataMusics: LiveData<ArrayList<Music>>
        get() = _lstDataMusics

    private lateinit var lst: ArrayList<Music>

    fun getListDataMusics() {
        lst = MusicsData.dataMusic()
        _lstDataMusics.postValue(lst)
    }
}