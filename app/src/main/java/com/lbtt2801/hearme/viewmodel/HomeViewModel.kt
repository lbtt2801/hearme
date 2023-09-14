package com.lbtt2801.hearme.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lbtt2801.hearme.data.ArtistsData
import com.lbtt2801.hearme.data.CategoriesData
import com.lbtt2801.hearme.data.FakeData
import com.lbtt2801.hearme.data.MusicsData
import com.lbtt2801.hearme.model.Artist
import com.lbtt2801.hearme.model.Category
import com.lbtt2801.hearme.model.Chart
import com.lbtt2801.hearme.model.Music

class HomeViewModel : ViewModel() {

    private val _lstDataMusic = MutableLiveData<List<Music>>()
    private val _lstDataArtist = MutableLiveData<List<Artist>>()
    private val _lstDataChart = MutableLiveData<List<Chart>>()
    private val _lstDataCategory = MutableLiveData<List<Category>>()

    val lstDataMusic: LiveData<List<Music>>
        get() = _lstDataMusic
    val lstDataArtist: LiveData<List<Artist>>
        get() = _lstDataArtist
    val lstDataChart: LiveData<List<Chart>>
        get() = _lstDataChart
    val lstDataCategory: LiveData<List<Category>>
        get() = _lstDataCategory


    private lateinit var lstMusic: ArrayList<Music>
    private lateinit var lstArtist: ArrayList<Artist>
    private lateinit var lstChart: ArrayList<Chart>
    private lateinit var lstCategory: ArrayList<Category>

    init {
        getListDataMusic()
        getListDataArtist()
        getListDataChart()
        getListDataCategory()
    }

    fun getListDataMusic() {
        lstMusic = MusicsData.dataMusic()
        _lstDataMusic.postValue(lstMusic)
    }

    fun getListDataArtist() {
        lstArtist = ArtistsData.dataArtist()
        _lstDataArtist.postValue(lstArtist)
    }

    fun getListDataChart() {
        lstChart = FakeData.dataChart()
        _lstDataChart.postValue(lstChart)
    }

    private fun getListDataCategory() {
        lstCategory = CategoriesData.dataCategory()
        _lstDataCategory.postValue(lstCategory)
    }
}