package com.lbtt2801.hearme.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lbtt2801.hearme.data.FakeData
import com.lbtt2801.hearme.model.Artist
import com.lbtt2801.hearme.model.Chart
import com.lbtt2801.hearme.model.Music

class HomeViewModel : ViewModel() {

    private val _lstDataMusic = MutableLiveData<List<Music>>()
    private val _lstDataArtist = MutableLiveData<List<Artist>>()
    private val _lstDataChart = MutableLiveData<List<Chart>>()
    val lstDataMusic: LiveData<List<Music>>
        get() = _lstDataMusic
    val lstDataArtist: LiveData<List<Artist>>
        get() = _lstDataArtist
    val lstDataChart: LiveData<List<Chart>>
        get() = _lstDataChart

    private lateinit var lstMusic: ArrayList<Music>
    private lateinit var lstArtist: ArrayList<Artist>
    private lateinit var lstChart: ArrayList<Chart>

    init {
        getListDataMusic()
        getListDataArtist()
        getListDataChart()
    }

    fun getListDataMusic() {
        lstMusic = FakeData.dataMusic()
        _lstDataMusic.postValue(lstMusic)
    }

    fun getListDataArtist() {
        lstArtist = FakeData.dataArtist()
        _lstDataArtist.postValue(lstArtist)
    }

    fun getListDataChart() {
        lstChart = FakeData.dataChart()
        _lstDataChart.postValue(lstChart)
    }


}