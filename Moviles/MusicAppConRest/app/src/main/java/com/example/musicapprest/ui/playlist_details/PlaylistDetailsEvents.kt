package com.example.musicapprest.ui.playlist_details

import com.example.musicapprest.domain.model.Playlist

interface PlaylistDetailsEvents {
    data class GetPlaylist (val playlistId : Int) : PlaylistDetailsEvents
    data class OnPlaylistNameChanged(val playlistName : String) : PlaylistDetailsEvents
    data object EventDone : PlaylistDetailsEvents
    data class UpdatePlaylist (val playlist : Playlist) : PlaylistDetailsEvents
    data class DeletePlaylist (val playlistId : Int) : PlaylistDetailsEvents
}