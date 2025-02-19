package com.example.musicapprest.ui.songs_list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.musicapprest.domain.model.Song
import com.example.musicapprest.ui.playlist_details.SongItem
import com.example.primeraapp.ui.common.UiEvent

@Composable
fun SongsListScreen(
    songsListViewModel: SongsListViewModel = hiltViewModel(),
    showSnackbar: (String) -> Unit = {}
) {
    val uiState by songsListViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        songsListViewModel.handleEvent(SongsListEvents.GetAll)
    }

    LaunchedEffect(uiState.event) {
        uiState.event?.let {
            if (it is UiEvent.ShowSnackbar) {
                showSnackbar(it.message)
            }
            songsListViewModel.handleEvent(SongsListEvents.EventDone)
        }
    }
    SongsListContent(
        songs = uiState.songs
    )
}

@Composable
fun SongsListContent(
    songs: List<Song> = emptyList(),
) {
    LazyColumn {
        this.items(items = songs, key = { song -> song.songId }) { song ->
            SongItem(song)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SongsListScreenPreview() {
    SongsListContent(
        songs = listOf(
            Song(1, "dsadsa", "dsadsa"),
            Song(2, "gfdgdf", "dsgdfgadsa"),
            Song(3, "dsadsabrhftdsa", "dsadsa"),
            Song(4, "dsaddsdasa", "dhtrhDSAsadsa"),
            Song(5, "dsadadshtyjyutukdsa", "dshgfhgfadsa")
        )
    )
}