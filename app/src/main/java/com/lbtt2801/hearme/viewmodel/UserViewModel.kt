package com.lbtt2801.hearme.viewmodel

import android.content.ContentValues.TAG
import android.graphics.Bitmap
import android.net.Uri
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
import com.squareup.picasso.Picasso
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
        updateFollowingList(lst[0].email, lst[1], true)
        updateFollowingList(lst[0].email, lst[4], true)
        updateFollowingList(lst[0].email, lst[6], true)
        updateFollowingList(lst[0].email, lst[8], true)
        updateFollowingList(lst[0].email, lst[14], true)
        updateFollowingList(lst[0].email, lst[15], true)
        updateFollowingList(lst[0].email, lst[16], true)

        updateFollowingList(lst[1].email, lst[0], true)
        updateFollowingList(lst[1].email, lst[12], true)
        updateFollowingList(lst[1].email, lst[13], true)
        updateFollowingList(lst[1].email, lst[5], true)
        updateFollowingList(lst[1].email, lst[6], true)

        updateFollowingList(lst[2].email, lst[1], true)
        updateFollowingList(lst[2].email, lst[3], true)
        updateFollowingList(lst[2].email, lst[10], true)

        updateFollowingList(lst[3].email, lst[1], true)
        updateFollowingList(lst[3].email, lst[2], true)
        updateFollowingList(lst[3].email, lst[5], true)
        updateFollowingList(lst[3].email, lst[6], true)
        updateFollowingList(lst[3].email, lst[12], true)

        updateFollowingList(lst[4].email, lst[1], true)
        updateFollowingList(lst[4].email, lst[6], true)
        updateFollowingList(lst[4].email, lst[7], true)
        updateFollowingList(lst[4].email, lst[8], true)
        updateFollowingList(lst[4].email, lst[9], true)
        updateFollowingList(lst[4].email, lst[12], true)
        updateFollowingList(lst[4].email, lst[16], true)

        updateFollowingList(lst[5].email, lst[3], true)
        updateFollowingList(lst[5].email, lst[4], true)
        updateFollowingList(lst[5].email, lst[5], true)
        updateFollowingList(lst[5].email, lst[9], true)

        updateFollowingList(lst[6].email, lst[1], true)
        updateFollowingList(lst[6].email, lst[13], true)
        updateFollowingList(lst[6].email, lst[14], true)
        updateFollowingList(lst[6].email, lst[15], true)
        updateFollowingList(lst[6].email, lst[16], true)

        updateFollowingList(lst[7].email, lst[2], true)
        updateFollowingList(lst[7].email, lst[3], true)
        updateFollowingList(lst[7].email, lst[5], true)
        updateFollowingList(lst[7].email, lst[7], true)
        updateFollowingList(lst[7].email, lst[10], true)
        updateFollowingList(lst[7].email, lst[11], true)

        updateFollowingList(lst[8].email, lst[12], true)
        updateFollowingList(lst[8].email, lst[13], true)
        updateFollowingList(lst[8].email, lst[3], true)
        updateFollowingList(lst[8].email, lst[15], true)

        updateFollowingList(lst[9].email, lst[1], true)
        updateFollowingList(lst[9].email, lst[2], true)

        updateFollowingList(lst[10].email, lst[3], true)
        updateFollowingList(lst[10].email, lst[5], true)
        updateFollowingList(lst[10].email, lst[7], true)
        updateFollowingList(lst[10].email, lst[12], true)

        _lstDataUser.postValue(lst)
    }

    fun addDataUser(email: String, pass: String) {
        lst.add(User(email = email, password = pass))
        _lstDataUser.postValue(lst)
        Log.v(TAG, "addDataUser -> ${lstDataUser.value?.size}")
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
        avatar: String? = null,
    ) {
        lst.first { it.email == email }.apply {
            this.fullName = fullName
            this.nickName = nickName
            this.dob = dob
            this.secondaryEmail = secondaryEmail
            this.phone = phoneNumber
            this.avatarUrl = avatar
        }
        _lstDataUser.postValue(lst)
    }

    fun updateIsFirstLogin(email: String, isFirst: Boolean) {
        lst.first { it.email == email }.apply {
            this.isFirstSignIn = isFirst
        }
        _lstDataUser.postValue(lst)
    }

    fun updateAvatar(email: String, url: String) {
        lst.first { it.email == email }.apply {
            this.avatarUrl = url
        }
        _lstDataUser.postValue(lst)
    }

    fun updateDataUser(
        email: String,
        fullName: String,
        nickName: String,
        dob: Date,
        phoneNumber: String,
        gender: Boolean,
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
                this.listPlaylist[0].listMusic.add(music)
            }
        } else {
            lst.first { it.email == email }.apply {
                this.listPlaylist[0].listMusic.removeIf { it.musicID == music.musicID }
            }
        }
        _lstDataUser.postValue(lst)
    }

    fun updateMusicsHistory(email: String, music: Music) {
        lst.first { it.email == email }.apply {
            this.listMusicsListened.add(music)
        }
        _lstDataUser.postValue(lst)
    }

    fun updateListMusicsQueued(email: String, music: Music, isQueue: Boolean) {
        if (isQueue) {
            lst.first { it.email == email }.apply {
                this.listMusicsQueue.add(music)
            }
        } else {
            lst.first { it.email == email }.apply {
                this.listMusicsQueue.removeIf { it.musicID == music.musicID }
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

    fun updatePlayingDownloaded(email: String, music: Music) {
        lst.first { it.email == email }.apply {
            this.listMusicsDownloaded.removeIf { it.musicID == music.musicID }
            this.listMusicsDownloaded.add(music)
        }

        _lstDataUser.postValue(lst)
    }

    fun updateBlackListMusic(email: String, music: Music, isDontPlay: Boolean) {
        if (isDontPlay) {
            lst.first { it.email == email }.apply {
                this.blackListMusic.add(music)
                this.listMusicsDownloaded.removeIf { it.musicID == music.musicID }
//                this.listPlayedMusic.removeIf { it.musicID == music.musicID }
                this.listMusicsQueue.removeIf { it.musicID == music.musicID }
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

    fun updateFollowingList(email: String, user: User, isFollow: Boolean) {
        if (isFollow) {
            lst.first { it.email == email }.apply {
                this.listUserFollowing.add(user)
            }
            Log.v(
                TAG,
                "updateFollowingList -> ${lst.first { it.email == email }.listUserFollowing.size}"
            )
            updateFollowersList(user.email, lst.first { it.email == email }, true)
        } else {
            lst.first { it.email == email }.apply {
                this.listUserFollowing.removeIf { it.email == user.email }
            }
            Log.v(
                TAG,
                "updateFollowingList -> ${lst.first { it.email == email }.listUserFollowing.size}"
            )
            updateFollowersList(user.email, lst.first { it.email == email }, false)
        }
        _lstDataUser.postValue(lst)
    }

    fun updateFollowersList(email: String, user: User, isFollow: Boolean) {
        if (isFollow) {
            lst.first { it.email == email }.apply {
                this.listFollowers.add(user)
            }
            Log.v(
                TAG,
                "updateFollowersList -> ${lst.first { it.email == email }.listFollowers.size}"
            )
        } else {
            lst.first { it.email == email }.apply {
                this.listFollowers.removeIf { it.email == user.email }
            }
            Log.v(
                TAG,
                "updateFollowersList -> ${lst.first { it.email == email }.listFollowers.size}"
            )
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

    fun getListDataPlaylist() {
        lstPlaylist = PlaylistData.dataPlaylist()
        _lstDataPlaylist.postValue(lstPlaylist)
    }

    fun addPlaylist(playlist: Playlist) {
        lstPlaylist.add(playlist)
        _lstDataPlaylist.postValue(lstPlaylist)
    }

    fun updatePlaying(email: String, music: Music, isPlaying: Boolean) {
        lst.first { it.email == email }.apply {
            listMusicsDownloaded.forEach {
                it.isPlaying = false
            }
            listMusicsDownloaded.first { it.musicID == music.musicID }.isPlaying = isPlaying
        }

        _lstDataUser.postValue(lst)
    }
}