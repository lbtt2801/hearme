package com.lbtt2801.hearme.data

import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.model.Artist
import com.lbtt2801.hearme.model.Music
import com.lbtt2801.hearme.model.Playlist
import com.lbtt2801.hearme.model.User
import java.util.*
import kotlin.collections.ArrayList

class UsersData {
    companion object {
        fun data(): ArrayList<User> {
            val data = ArrayList<User>()
            data.apply {
                add(
                    User(
                        "phuongviet.huit@gmail.com",
                        "phuongviet123456",
                        "awdssdfgviethufi@gmail.com",
                        R.drawable.avatar_1,
                        null,
                        "Nguyen Phuong Viet",
                        "Viet Huit",
                        Date("06/12/2002"),
                        "VN",
                        "93 982 63 76",
                        1234,
                        true, //true is male, false is female
                        true,
                        false, // true if user the first sign in
                        ArtistsData.dataArtist() //listArtistsFollowing
                            .filter { it.artistId == " ar001" || it.artistId == "ar002" || it.artistId == "ar004" } as ArrayList<Artist>,
                        ArrayList(), //listFollowers
//                        UsersData.data() //listFollowers
//                            .filter { it.email == "lebuitantruong@gmail.com" } as ArrayList<User>,
                        MusicsData.dataMusic() //listMusicsLoved
                            .filter { it.musicID == "ms001" || it.musicID == "ms002" || it.musicID == "ms005" || it.musicID == "ms008" } as ArrayList<Music>,
                        MusicsData.dataMusic() //listMusicsListened
                            .filter { it.musicID == "ms001" || it.musicID == "ms002" || it.musicID == "ms005" || it.musicID == "ms008" || it.musicID == "ms011" } as ArrayList<Music>,
                        MusicsData.dataMusic() //listMusicsDownloaded
                            .filter { it.musicID == "ms004" || it.musicID == "ms009" } as ArrayList<Music>,
                        ArrayList(), //listMusicsQueued
                        PlaylistData.dataPlaylist()
                            .filter { it.playlistID == "pl001" || it.playlistID == "pl003" } as ArrayList<Playlist>,
//                        MusicsData.dataMusic() //listPlayedMusic
//                            .filter { it.musicID == "ms004" || it.musicID == "ms010" || it.musicID == "ms012" || it.musicID == "ms009" } as ArrayList<Music>,
                        ArrayList() //blackListMusic
                    )
                )
                add(
                    User(
                        "lebuitantruong@gmail.com",
                        "lbtt2801",
                        "truongletanbui@gmail.com",
                        R.drawable.avatar_1,
                        null,
                        "Le Bui Tan Truong",
                        "Truong HUIT",
                        Date("28/12/2002"),
                        "VN",
                        "32 846 79 24",
                        4567,
                        true, // true is male, false is female,
                        true,
                        false, // true if user the first sign in
                        ArtistsData.dataArtist()//listArtistsFollowing
                            .filter { it.artistId == " ar003" || it.artistId == "ar004" } as ArrayList<Artist>,
                        ArrayList(),
//                        UsersData.data() //listFollowers
//                            .filter { it.email == "phuongviet.huit@gmail.com" } as ArrayList<User>,
                        MusicsData.dataMusic() //listMusicsLoved
                            .filter { it.musicID == "ms002" || it.musicID == "ms006" || it.musicID == "ms007" } as ArrayList<Music>,
                        MusicsData.dataMusic() //listMusicListened
                            .filter { it.musicID == "ms002" || it.musicID == "ms006" || it.musicID == "ms007" || it.musicID == "ms001" || it.musicID == "ms004" } as ArrayList<Music>,
                        MusicsData.dataMusic() //listMusicDownloaded
                            .filter { it.musicID == "ms003" || it.musicID == "ms012" || it.musicID == "ms009" } as ArrayList<Music>,
                        MusicsData.dataMusic() //listMusicsQueued
                            .filter { it.musicID == "ms002" || it.musicID == "ms006" } as ArrayList<Music>,
                        PlaylistData.dataPlaylist() //listMusicsPlaylist
                            .filter { it.playlistID == "pl001" || it.playlistID == "pl002" || it.playlistID == "pl003" } as ArrayList<Playlist>,
//                        MusicsData.dataMusic() //listPlayedMusic
//                            .filter { it.musicID == "ms003" || it.musicID == "ms009" || it.musicID == "ms010" } as ArrayList<Music>,
                        ArrayList() //blackListMusic
                    )
                )
                add(
                    User(
                        "123456@gmail.com",
                        "123456",
                        "b@gmail.com",
                        R.drawable.avatar_2,
                        null,
                        "Account Clone",
                        "Truong HUIT",
                        Date("28/01/2002"),
                        "VN",
                        "32 846 79 25",
                        4567,
                        true, // true is male, false is female,
                        false,
                        false, // true if user the first sign in
                        ArtistsData.dataArtist()//listArtistsFollowing
                            .filter { it.artistId == " ar005" || it.artistId == "ar006" } as ArrayList<Artist>,
                        ArrayList(),
//                        UsersData.data() //listFollowers
//                            .filter { it.email == "phuongviet.huit@gmail.com" || it.email == "lebuitantruong@gmail.com" } as ArrayList<User>,
                        MusicsData.dataMusic() //listMusicsLoved
                            .filter { it.musicID == "ms009" || it.musicID == "ms010" || it.musicID == "ms011" } as ArrayList<Music>,
                        MusicsData.dataMusic() //listMusicListened
                            .filter { it.musicID == "ms009" || it.musicID == "ms007" || it.musicID == "ms005" || it.musicID == "ms010" || it.musicID == "ms011" } as ArrayList<Music>,
                        MusicsData.dataMusic() //listMusicDownloaded
                            .filter { it.musicID == "ms005" || it.musicID == "ms004" || it.musicID == "ms001" } as ArrayList<Music>,
                        ArrayList(), //listMusicsQueued
                        PlaylistData.dataPlaylist()
                            .filter { it.playlistID == "pl001" || it.playlistID == "pl002" } as ArrayList<Playlist>,
//                        MusicsData.dataMusic() //listPlayedMusic
//                            .filter { it.musicID == "ms002" || it.musicID == "ms003" || it.musicID == "ms007" } as ArrayList<Music>,
                        ArrayList() //blackListMusic
                    )
                )
                add(
                    User(
                        "userso1@gmail.com",
                        "userso1",
                        "b@gmail.com",
                        R.drawable.avatar_3,
                        null,
                        "User so 1",
                        "Acc Clone",
                        Date("28/01/2002"),
                        "VN",
                        "32 846 79 25",
                        4567,
                        true, // true is male, false is female,
                        false,
                        false, // true if user the first sign in
                    )
                )
                add(
                    User(
                        "userso2@gmail.com",
                        "userso2",
                        "b@gmail.com",
                        R.drawable.avatar_4,
                        null,
                        "User so 2",
                        "Acc Clone",
                        Date("28/01/2002"),
                        "VN",
                        "32 846 79 25",
                        4567,
                        true, // true is male, false is female,
                        false,
                        false, // true if user the first sign in
                    )
                )
                add(
                    User(
                        "userso3@gmail.com",
                        "userso3",
                        "b@gmail.com",
                        R.drawable.avatar_1,
                        null,
                        "User so 3",
                        "Acc Clone",
                        Date("28/01/2002"),
                        "VN",
                        "32 846 79 25",
                        4567,
                        true, // true is male, false is female,
                        false,
                        false, // true if user the first sign in
                    )
                )
                add(
                    User(
                        "userso4@gmail.com",
                        "userso4",
                        "b@gmail.com",
                        R.drawable.avatar_2,
                        null,
                        "User so 4",
                        "Acc Clone",
                        Date("28/01/2002"),
                        "VN",
                        "32 846 79 25",
                        4567,
                        true, // true is male, false is female,
                        false,
                        false, // true if user the first sign in
                    )
                )
                add(
                    User(
                        "userso5@gmail.com",
                        "userso5",
                        "b@gmail.com",
                        R.drawable.avatar_3,
                        null,
                        "User so 5",
                        "Acc Clone",
                        Date("28/01/2002"),
                        "VN",
                        "32 846 79 25",
                        4567,
                        true, // true is male, false is female,
                        false,
                        false, // true if user the first sign in
                    )
                )
                add(
                    User(
                        "userso6@gmail.com",
                        "userso6",
                        "b@gmail.com",
                        R.drawable.avatar_4,
                        null,
                        "User so 6",
                        "Acc Clone",
                        Date("28/01/2002"),
                        "VN",
                        "32 846 79 25",
                        4567,
                        true, // true is male, false is female,
                        false,
                        false, // true if user the first sign in
                    )
                )
            }
            return data
        }
    }
}