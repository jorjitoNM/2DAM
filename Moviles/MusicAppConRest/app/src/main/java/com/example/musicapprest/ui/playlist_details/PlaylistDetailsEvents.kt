package com.example.musicapprest.ui.playlist_details

interface PlaylistDetailsEvents {
    data class GetPlaylist (val playlistId : Int) : PlaylistDetailsEvents
    data class OnPlaylistNameChanged(val playlistName : String) : PlaylistDetailsEvents
    data object EventDone : PlaylistDetailsEvents
    data object UpdatePlaylist : PlaylistDetailsEvents
    data object DeletePlaylist : PlaylistDetailsEvents
    data object AddPlaylists : PlaylistDetailsEvents
}