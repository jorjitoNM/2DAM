package com.example.musicapprest.ui.playlist_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.musicapprest.R
import com.example.musicapprest.domain.model.Playlist
import com.example.musicapprest.domain.model.Song
import com.example.primeraapp.ui.common.UiEvent

@Composable
fun PlaylistDetailsScreen(
    playlistdId: Int = 1,
    playlistDetailsViewModel: PlaylistDetailsViewModel = hiltViewModel(),
    showSnackbar: (String) -> Unit = {},
) {

    val uiState by playlistDetailsViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        playlistDetailsViewModel.handleEvent(PlaylistDetailsEvents.GetPlaylist(playlistdId))
    }

    LaunchedEffect(uiState.event) {
        uiState.event?.let {
            if (it is UiEvent.ShowSnackbar) {
                showSnackbar(it.message)
            }
            playlistDetailsViewModel.handleEvent(PlaylistDetailsEvents.EventDone)
        }
    }

    PlaylistDetailsContent(
        playlist = uiState.playlist,
        onPlaylistNameChange = { newPlaylistName ->
            playlistDetailsViewModel.handleEvent(
                PlaylistDetailsEvents.OnPlaylistNameChanged(
                    newPlaylistName
                )
            )
        },
        updatePlaylist = { playlistDetailsViewModel.handleEvent(PlaylistDetailsEvents.UpdatePlaylist(uiState.playlist)) }
    )
}

@Composable
fun PlaylistDetailsContent(
    playlist: Playlist = Playlist(),
    onPlaylistNameChange: (String) -> Unit = {},
    updatePlaylist : () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .weight(0.1f)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(playlist.owner.split("@")[0] + stringResource(R.string.playlist_s), style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onPrimaryContainer)
        }
        Row(
            modifier = Modifier
                .weight(0.15f)
                .fillMaxSize()
                .padding(dimensionResource(R.dimen.padding8)),
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .weight(0.2f)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("ID: " + playlist.playlistId, color = MaterialTheme.colorScheme.secondary)
            }
            Column(
                modifier = Modifier
                    .weight(0.8f)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = playlist.playlistName,
                    onValueChange = onPlaylistNameChange,
                    label = { Text(stringResource(R.string.playlist_name)) },
                    singleLine = true
                )
            }
        }
        Row(
            modifier = Modifier.weight(0.55f).fillMaxSize().padding(dimensionResource(R.dimen.padding8)),
            horizontalArrangement = Arrangement.Center
        ) {
            LazyColumn (contentPadding = PaddingValues(dimensionResource(R.dimen.padding8))) {
                this.items(items = playlist.songs, key = { song -> song.songId }) { song ->
                    SongItem(song)
                }
            }
        }
        Row (
            modifier = Modifier.weight(0.2f).fillMaxSize().padding(dimensionResource(R.dimen.padding8)),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = updatePlaylist, colors = ButtonColors(
                MaterialTheme.colorScheme.primaryContainer,
                MaterialTheme.colorScheme.onPrimaryContainer,
                MaterialTheme.colorScheme.onSecondaryContainer,
                MaterialTheme.colorScheme.onSecondaryContainer,)) {
                Text(stringResource(R.string.update))
            }
        }
    }
}

@Composable
fun SongItem(
    song: Song = Song()
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding8))
    ) {
        Column(
            modifier = Modifier
                .weight(0.8f)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    song.songId.toString() + " " + song.songName,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(song.artist, style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.secondary)

            }
        }
        Column(
            modifier = Modifier
                .weight(0.2f)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
                Icon(
                    Icons.Default.MoreVert,
                    contentDescription = stringResource(id = R.string.more_actions)
                )
        }
    }
    HorizontalDivider()
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PlaylistDetailsScreenPreview(
    playlist: Playlist = Playlist(),
) {
    PlaylistDetailsContent(
        playlist = Playlist(
            1, "This is Kendrick Lamar",listOf(
                Song(1, "Not Like Us", "Kendrick lamar"),
                Song(2, "Money In The Grave", "Drake, Rick Ross")
            )
        )
    )
}