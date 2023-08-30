package com.lbtt2801.hearme.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.model.User
import java.util.Date

class SignUpViewModel(): ViewModel() {
    private val _lstDataUser = MutableLiveData<List<User>>()
    val lstDataUser: LiveData<List<User>>
        get() = _lstDataUser

//    fun addDataUser(email: String, pass: String) {
//        var lst = UserData.data()
//        lst.add(User(email, pass, R.drawable.logo,"45","54", Date(2002, 8, 30, 0, 0, 0),"VN","55",2345,38,32,false))
//        Log.v(TAG, "size: ${lst.size}")
//        _lstDataUser.postValue(lst)
//    }

    fun addUser(email: String, pass: String): User {
        return User(
            email,
            pass,
            R.drawable.logo,
            "45",
            "54",
            Date(2002, 8, 30, 0, 0, 0),
            "VN",
            "55",
            2345,
            38,
            32,
            false
        )
    }

//    User("lbb","123", R.drawable.logo,"45","54", Date(2002, 12, 6, 0, 0, 0),"VN","55",2345,38,32,false)
}