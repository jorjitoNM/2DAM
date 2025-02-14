package com.example.musicappcompse.ui.playlist_details

interface PlaylistDetailsEvents {
    data class GetPlaylist (private val playlistId : Int) : PlaylistDetailsEvents
    data object EventDone : PlaylistDetailsEvents
}