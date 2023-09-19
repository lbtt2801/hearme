package com.lbtt2801.hearme.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.data.PlaylistData
import com.lbtt2801.hearme.data.UsersData
import com.lbtt2801.hearme.model.Artist
import com.lbtt2801.hearme.model.Music
import com.lbtt2801.hearme.model.Playlist
import com.lbtt2801.hearme.model.User
import java.util.Date

class UserViewModel : ViewModel() {
    private val _lstDataUser = MutableLiveData<ArrayList<User>>()
    private val _lstDataPlaylist = MutableLiveData<List<Playlist>>()

    val lstDataUser: LiveData<ArrayList<User>>
        get() = _lstDataUser

    val lstDataPlaylist: LiveData<List<Playlist>>
        get() = _lstDataPlaylist

    private lateinit var lst: ArrayList<User>
    private lateinit var lstPlaylist: ArrayList<Playlist>

    init {
        getListDataUser()
        getListDataPlaylist()
    }

    private fun getListDataUser() {
        lst = UsersData.data()
        lst.first { it.email == "phuongviet.huit@gmail.com" }.apply {
            this.listFollowers.add(
                UsersData.data().first { it.email == "lebuitantruong@gmail.com" })
        }
        lst.first { it.email == "lebuitantruong@gmail.com" }.apply {
            this.listFollowers.add(
                UsersData.data().first { it.email == "phuongviet.huit@gmail.com" })
        }
        lst.first { it.email == "123456@gmail.com" }.apply {
            this.listFollowers.apply {
                add(UsersData.data().first { it.email == "phuongviet.huit@gmail.com" })
                add(UsersData.data().first { it.email == "lebuitantruong@gmail.com" })

            }
        }
        _lstDataUser.postValue(lst)
    }

    fun addDataUser(email: String, pass: String) {
        lst.add(User(email, pass, null, R.drawable.avt_home))
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
        phoneNumber: String,
        image: Int = R.drawable.avt_home
    ) {
        lst.first { it.email == email }.apply {
            this.fullName = fullName
            this.nickName = nickName
            this.dob = dob
            this.secondaryEmail = secondaryEmail
            this.phone = phoneNumber
            this.avatar = image
        }
        _lstDataUser.postValue(lst)
    }

    fun updateDataUser(
        email: String,
        fullName: String,
        nickName: String,
        dob: Date,
        phoneNumber: String,
        gender: Boolean
    ) {
        lst.first { it.email == email }.apply {
            this.fullName = fullName
            this.nickName = nickName
            this.dob = dob
            this.phone = phoneNumber
            this.gender = gender
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
        } else {
            lst.first { it.email == email }.apply {
                this.listArtistsFollowing.remove(artist)
            }
        }
        _lstDataUser.postValue(lst)
        Log.v(
            TAG, "updateFollowingArtists -> ${
                lstDataUser.value?.first { it.email == email }?.listArtistsFollowing
            }"
        )
    }

    fun updatePassword(email: String, password: String) {
        lst.first { it.email == email }.apply {
            this.password = password
        }
        _lstDataUser.postValue(lst)
        Log.v(TAG, "${lstDataUser.value.toString()}")
    }

    fun updateListMusicsLoved(email: String, music: Music, isLove: Boolean) {
        if (isLove) {
            lst.first { it.email == email }.apply {
                this.listMusicsLoved.add(music)
            }
        } else {
            lst.first { it.email == email }.apply {
                this.listMusicsLoved.removeIf { it.musicID == music.musicID }
            }
        }
        _lstDataUser.postValue(lst)
        Log.v(
            TAG,
            "blackListMusic -> ${lstDataUser.value?.first { it.email == email }?.blackListMusic?.size}"
        )
        Log.v(
            TAG,
            "listMusicsLoved -> ${lstDataUser.value?.first { it.email == email }?.listMusicsLoved?.size}"
        )
    }

    fun updateListPlayedMusic(email: String, music: Music, isLove: Boolean) {
        if (isLove) {
            lst.first { it.email == email }.apply {
//                this.listPlayedMusic.add(music)
            }
        } else {
            lst.first { it.email == email }.apply {
//                this.listPlayedMusic.removeIf { it.musicID == music.musicID }
            }
        }
        _lstDataUser.postValue(lst)
    }

    fun updateListMusicsDownloaded(email: String, music: Music, isDownloaded: Boolean) {
        if (isDownloaded) {
            lst.first { it.email == email }.apply {
                this.listMusicsDownloaded.add(music)
            }
        } else {
            lst.first { it.email == email }.apply {
                this.listMusicsDownloaded.removeIf { it.musicID == music.musicID }
            }
        }
        _lstDataUser.postValue(lst)
    }

    fun updateBlackListMusic(email: String, music: Music, isDontPlay: Boolean) {
        if (isDontPlay) {
            lst.first { it.email == email }.apply {
                this.blackListMusic.add(music)
                this.listMusicsDownloaded.removeIf { it.musicID == music.musicID }
//                this.listPlayedMusic.removeIf { it.musicID == music.musicID }
                this.listMusicsLoved.removeIf { it.musicID == music.musicID }
                this.listMusicsListened.removeIf { it.musicID == music.musicID }
            }
        } else {
            lst.first { it.email == email }.apply {
                this.blackListMusic.removeIf { it.musicID == music.musicID }
            }
        }
        _lstDataUser.postValue(lst)
        Log.v(
            TAG,
            "blackListMusic -> ${lstDataUser.value?.first { it.email == email }?.blackListMusic?.size}"
        )
        Log.v(
            TAG,
            "listMusicsDownloaded -> ${lstDataUser.value?.first { it.email == email }?.listMusicsDownloaded?.size}"
        )
//        Log.v(
//            TAG,
//            "listPlayedMusic -> ${lstDataUser.value?.first { it.email == email }?.listPlayedMusic?.size}"
//        )
        Log.v(
            TAG,
            "listMusicsLoved -> ${lstDataUser.value?.first { it.email == email }?.listMusicsLoved?.size}"
        )
        Log.v(
            TAG,
            "listMusicsListened -> ${lstDataUser.value?.first { it.email == email }?.listMusicsListened?.size}"
        )
    }

    fun isMusicInBlackList(email: String, music: Music): Boolean {
        return !lstDataUser.value?.first { it.email == email }?.blackListMusic?.none { it.musicID == music.musicID }!!
    }

    fun updateFollowersList(email: String, user: User, isFollow: Boolean) {
        if (isFollow) {
            lst.first { it.email == email }.apply {
                this.listFollowers.add(user)
            }
        } else {
            lst.first { it.email == email }.apply {
                this.listFollowers.removeIf { it.email == user.email }
            }
        }
        _lstDataUser.postValue(lst)
    }

    fun updateFollowingArtistList(email: String, artist: Artist, isFollowing: Boolean) {
        if (isFollowing) {
            lst.first { it.email == email }.apply {
                this.listArtistsFollowing.add(artist)
            }
        } else {
            lst.first { it.email == email }.apply {
                this.listArtistsFollowing.removeIf { it.artistId == artist.artistId }
            }
        }
        _lstDataUser.postValue(lst)
    }

    private fun getListDataPlaylist() {
        lstPlaylist = PlaylistData.dataPlaylist()
        _lstDataPlaylist.postValue(lstPlaylist)
    }
    fun addPlaylist(playlist: Playlist) {
        lstPlaylist.add(0, playlist)
        _lstDataPlaylist.postValue(lstPlaylist)
    }
}