package com.lbtt2801.hearme.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lbtt2801.hearme.model.Music

class SongPlayViewModel : ViewModel() {
    private val mutableSelectedItem = MutableLiveData<Music>()
    val selectedItem: LiveData<Music> get() = mutableSelectedItem

    fun selectItem(music: Music) {
        mutableSelectedItem.value = music
    }
}