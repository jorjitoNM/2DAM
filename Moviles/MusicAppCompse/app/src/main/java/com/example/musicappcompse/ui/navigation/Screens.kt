package com.example.musicappcompse.ui.navigation


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.musicappcompse.ui.common.Constantes
import com.example.playlistcompose.ui.navigation.ScaffoldState
import com.example.playlistcompose.ui.navigation.TopBarState

val appDestinationList = listOf(
    LoginScreen,
    PlaylistListScreen,
    PlaylistDetailsScreen,
    SongsListScreen,
)

interface AppDestination{
    val route: Any
    val title: String
    val scaffoldState: ScaffoldState
        get() = ScaffoldState(
            topBarState = TopBarState(showNavigationIcon = true, arrangement = Arrangement.Start),
            fabVisible = true
        )
}

interface AppMainBottomDestination : AppDestination {
    val onBottomBar: Boolean
    val icon: ImageVector
}

object LoginScreen : AppDestination {
    override val route = LoginScreenDestination
    override val title = Constantes.LOGIN
    override val scaffoldState = ScaffoldState(
        topBarState = TopBarState(showNavigationIcon = false, arrangement = Arrangement.Start),
        fabVisible = false,
        bottomBarVisible = false,
    )
}

object PlaylistListScreen : AppMainBottomDestination {
    override val onBottomBar: Boolean = true
    override val icon = Icons.AutoMirrored.Sharp.List
    override val route = PlaylistListScreenDestination
    override val title = Constantes.PLAYLIST_LIST
    override val scaffoldState = ScaffoldState(
        topBarState = TopBarState(showNavigationIcon = false, arrangement = Arrangement.Start),
        fabVisible = true,
        bottomBarVisible = true,
    )

}

object PlaylistDetailsScreen : AppDestination {
    override val route = PlaylistDetailsScreenDestination
    override val title = Constantes.PLAYLIST_DETAILS
    override val scaffoldState = ScaffoldState(
        topBarState = TopBarState(showNavigationIcon = true, arrangement = Arrangement.Start),
        fabVisible = false,
        bottomBarVisible = false,
    )
}

object SongsListScreen : AppMainBottomDestination {
    override val onBottomBar: Boolean = true
    override val icon = Icons.Default.Favorite
    override val route = SongsListScreenDestination
    override val title = Constantes.SONGS_LIST
    override val scaffoldState = ScaffoldState(
        topBarState = TopBarState(showNavigationIcon = false, arrangement = Arrangement.Start),
        fabVisible = true,
        bottomBarVisible = true,
    )
}