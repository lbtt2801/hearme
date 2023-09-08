package com.lbtt2801.hearme.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lbtt2801.hearme.data.CategoriesData
import com.lbtt2801.hearme.model.Category

class CategoriesViewModel : ViewModel() {
    private val _lstDataCategories = MutableLiveData<ArrayList<Category>>()
    val lstDataCategories: LiveData<ArrayList<Category>>
        get() = _lstDataCategories

    private lateinit var lst: ArrayList<Category>

    fun getListDataCategories() {
        lst = CategoriesData.dataCategory()
        _lstDataCategories.postValue(lst)
    }
}