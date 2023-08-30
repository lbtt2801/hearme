package com.lbtt2801.hearme.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.data.UserData
import com.lbtt2801.hearme.model.User
import java.util.Date

class SignUpViewModel(): ViewModel() {
    private var _lstUserLiveData = MutableLiveData<List<User>>()
    val lstUserLiveData: LiveData<List<User>> get() = _lstUserLiveData
    var lstUser = listOf<User>()
    fun signUpUser() {
        lstUser = UserData.data()
        _lstUserLiveData.postValue(lstUser)
    }
//    User("lbb","123", R.drawable.logo,"45","54", Date(2002, 12, 6, 0, 0, 0),"VN","55",2345,38,32,false)
}