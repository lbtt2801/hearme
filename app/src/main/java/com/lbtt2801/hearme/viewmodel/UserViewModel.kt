package com.lbtt2801.hearme.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.data.UserData
import com.lbtt2801.hearme.model.User
import java.util.Date

class UserViewModel : ViewModel() {
    private val _lstDataUser = MutableLiveData<List<User>>()
    val lstDataUser: LiveData<List<User>>
        get() = _lstDataUser

    private lateinit var lst: ArrayList<User>

    init {
        getListDataUser()
    }

//    fun initUser() {
//        userArrayList = UserData.dataUser
//        userMutableLiveData.value = userArrayList
//    }

    fun getListDataUser() {
        lst = UserData.data
        _lstDataUser.postValue(lst)
    }

    fun addDataUser(email: String, pass: String) {
        lst.add(User(email, pass))
        Log.v(ContentValues.TAG, "size 00: ${lst.size}")
        _lstDataUser.postValue(lst)
    }
}