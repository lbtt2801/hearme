package com.lbtt2801.hearme.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lbtt2801.hearme.data.UserData
import com.lbtt2801.hearme.model.User

class SignInViewModel: ViewModel() {
    private val _lstDataUser = MutableLiveData<List<User>>()
    val lstDataUser: LiveData<List<User>>
        get() = _lstDataUser

    fun getListDataUser() {
        val lst = UserData.dataUser
        _lstDataUser.postValue(lst)
    }
}