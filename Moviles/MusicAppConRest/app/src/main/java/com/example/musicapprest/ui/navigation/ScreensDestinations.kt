package com.example.musicapprest.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object LoginScreenDestination

@Serializable
object PlaylistListScreenDestination

@Serializable
data class PlaylistDetailsScreenDestination(val playlistId : Int)

@Serializable
object SongsListScreenDestination