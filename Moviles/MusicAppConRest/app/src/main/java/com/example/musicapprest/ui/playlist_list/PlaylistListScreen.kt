package com.example.musicapprest.ui.playlist_list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
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
import com.example.playlistcompose.ui.playlist_list.PlaylistListEvents
import com.example.primeraapp.ui.common.UiEvent

@Composable
fun PlaylistListScreen(
    playlistListViewModel: PlaylistListViewModel = hiltViewModel(),
    showSnackbar: (String) -> Unit = {},
    onNavigateDetail: (Int) -> Unit = {},
) {
    val uiState by playlistListViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        playlistListViewModel.handleEvent(PlaylistListEvents.GetAll)
    }

    LaunchedEffect(uiState.event) {
        uiState.event?.let {
            if (it is UiEvent.ShowSnackbar) {
                showSnackbar(it.message)
            }
            playlistListViewModel.handleEvent(PlaylistListEvents.EventDone)
        }
    }

    PlaylistListContent(
        playlists = uiState.playlists,
        onNavigateDetail = onNavigateDetail,
    )
}

@Composable
fun PlaylistListContent(
    playlists: List<Playlist> = emptyList(),
    onNavigateDetail: (Int) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn {
            this.items(items = playlists, key = { playlist -> playlist.playlistId }) { playlist ->
                PlaylistItem(playlist, onNavigateDetail)
            }
        }
    }

}

@Composable
fun PlaylistItem(
    playlist: Playlist = Playlist(),
    onNavigateDetail: (Int) -> Unit,
) {
    OutlinedCard(
        colors = CardDefaults.outlinedCardColors(),
        border = BorderStroke(dimensionResource(R.dimen.border1dp), MaterialTheme.colorScheme.onSecondaryContainer),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(dimensionResource(R.dimen.padding8))
            .clickable(onClick = { onNavigateDetail(playlist.playlistId) }),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding8))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = stringResource(R.string.name) + " " + playlist.playlistName,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding4))
                    )
                    Text(
                        text = stringResource(R.string.id) + " " + playlist.playlistId,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PlaylistListScreenPreview() {
    PlaylistListContent(
        playlists = listOf(
            Playlist(1, "dsada"),
            Playlist(2, "fdsfds"),
            Playlist(3, "fbhtrhtyjyt"),
        )
    ) {}
}