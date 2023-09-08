package com.lbtt2801.hearme.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lbtt2801.hearme.data.UsersData
import com.lbtt2801.hearme.model.Artist
import com.lbtt2801.hearme.model.User
import java.util.Date

class UserViewModel : ViewModel() {
    private val _lstDataUser = MutableLiveData<ArrayList<User>>()
    val lstDataUser: LiveData<ArrayList<User>>
        get() = _lstDataUser

    private lateinit var lst: ArrayList<User>

    init {
        getListDataUser()
    }

    fun getListDataUser() {
        lst = UsersData.data()
        _lstDataUser.postValue(lst)
    }

    fun addDataUser(email: String, pass: String) {
        lst.add(User(email, pass))
        _lstDataUser.postValue(lst)
        Log.v(TAG, "${lstDataUser.value.toString()}")
    }

    fun checkDuplicateEmails(email: String): Boolean {
        if (lst.find { it.email == email } != null)
            return true
        return false
    }

    fun updateUserInfo(
        email: String,
        fullName: String,
        nickName: String,
        dob: Date,
        secondaryEmail: String,
        phoneNumber: String
    ) {
        lst.first { it.email == email }.apply {
            this.fullName = fullName
            this.nickName = nickName
            this.dob = dob
            this.secondaryEmail = secondaryEmail
            this.phone = phoneNumber
        }
        _lstDataUser.postValue(lst)
    }

    fun updateUserPin(email: String, pin: Int) {
        lst.first { it.email == email }.apply {
            this.pin = pin
        }
        _lstDataUser.postValue(lst)
    }

    fun updateFollowingArtists(email: String, artist: Artist, isFollow: Boolean) {
        if (isFollow) {
            lst.first { it.email == email }.apply {
                this.listArtistsFollowing.add(artist)
            }
            _lstDataUser.postValue(lst)
        } else {
            lst.first { it.email == email }.apply {
                this.listArtistsFollowing.remove(artist)
            }
            _lstDataUser.postValue(lst)
        }
        Log.v(TAG, "lst -> $lst")
        Log.v(TAG, "_lstDataUser -> ${_lstDataUser.value.toString()}")
        Log.v(TAG, "lstDataUser -> ${lstDataUser.value.toString()}")
    }

    fun updatePassword(email: String, password: String) {
        lst.first { it.email == email }.apply {
            this.password = password
        }
        _lstDataUser.postValue(lst)
        Log.v(TAG, "${lstDataUser.value.toString()}")
    }
}