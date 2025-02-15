package com.example.musicappcompse.ui.playlist_details

import com.example.musicappcompse.domain.model.Playlist

interface PlaylistDetailsEvents {
    data class GetPlaylist (val playlistId : Int) : PlaylistDetailsEvents
    data class OnPlaylistNameChanged(val playlistName : String) : PlaylistDetailsEvents
    data object EventDone : PlaylistDetailsEvents
    data object GetUserName : PlaylistDetailsEvents
    data class UpdatePlaylist (val playlist : Playlist) : PlaylistDetailsEvents
}