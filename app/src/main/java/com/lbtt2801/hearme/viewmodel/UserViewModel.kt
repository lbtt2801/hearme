package com.lbtt2801.hearme.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lbtt2801.hearme.data.UserData
import com.lbtt2801.hearme.model.User

class UserViewModel : ViewModel() {
    private val userMutableLiveData: MutableLiveData<ArrayList<User>> = MutableLiveData()
    lateinit var userArrayList: ArrayList<User>

    init {
        initUser()
    }

    fun initUser() {
        userArrayList = UserData.data()
        userMutableLiveData.value = userArrayList
    }
}