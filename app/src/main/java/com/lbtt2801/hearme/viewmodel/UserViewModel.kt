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

    private val userMutableLiveData: MutableLiveData<ArrayList<User>> = MutableLiveData()
    lateinit var userArrayList: ArrayList<User>

    init {
        getListDataUser()
    }

//    fun initUser() {
//        userArrayList = UserData.dataUser
//        userMutableLiveData.value = userArrayList
//    }

    fun getListDataUser() {
        val lst = UserData.dataUser
        _lstDataUser.postValue(lst)
    }

    fun addDataUser(email: String, pass: String) {
        val lst = UserData.dataUser
        lst.add(User(email, pass, R.drawable.logo,"45","54", Date(2002, 8, 30, 0, 0, 0),"VN","55",2345,38,32,false))
        Log.v(ContentValues.TAG, "size: ${lst.size}")
        _lstDataUser.postValue(lst)
    }
}