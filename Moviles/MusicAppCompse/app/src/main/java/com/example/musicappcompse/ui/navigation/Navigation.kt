package com.example.musicappcompse.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.musicappcompse.ui.common.BottomBar
import com.example.musicappcompse.ui.common.TopBar
import com.example.musicappcompse.ui.login.LoginScreen
import com.example.musicappcompse.ui.playlist_details.PlaylistDetailsScreen
import com.example.musicappcompse.ui.playlist_list.PlaylistListScreen
import com.example.musicappcompse.ui.songs_list.SongsListScreen
import kotlinx.coroutines.launch

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val showSnackbar = { message: String ->
        scope.launch {
            snackbarHostState.showSnackbar(
                message,
                duration = SnackbarDuration.Short
            )
        }
    }

    val state by navController.currentBackStackEntryAsState()

    var isBottomBarVisible by rememberSaveable { mutableStateOf(true) }
    var isTopBarVisible by rememberSaveable { mutableStateOf(true) }

    val screen = appDestinationList.find { screen ->
        state?.destination?.route == screen.route::class.qualifiedName
    }

    val bottomBar: @Composable () -> Unit = {
        BottomBar(
            navController = navController,
            screens = appDestinationList,
            isVisible = isBottomBarVisible
        )
    }
    val topBar: @Composable () -> Unit = {
        TopBar(
            navController = navController,
            screen = screen,
            isVisible = isTopBarVisible,
        )
    }
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = bottomBar,
        topBar = topBar,
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = LoginScreenDestination,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<LoginScreenDestination> {
                isBottomBarVisible =  false
                isTopBarVisible = false
                LoginScreen(
                    navController = navController,
                    showSnackbar = { showSnackbar(it) }
                )
            }
            composable<PlaylistListScreenDestination> {
                isBottomBarVisible =  true
                isTopBarVisible = true
                PlaylistListScreen(
                    showSnackbar = { showSnackbar(it)},
                    onNavigateDetail = {playlistId ->
                        navController.navigate(PlaylistDetailsScreenDestination(playlistId))
                    }
                )
            }
            composable<PlaylistDetailsScreenDestination> {
                isBottomBarVisible =  false
                isTopBarVisible = true
                PlaylistDetailsScreen(
                    playlistdId = (it.toRoute() as PlaylistDetailsScreenDestination).playlistId,
                    showSnackbar = { showSnackbar(it)},
                )
            }
            composable<SongsListScreenDestination> {
                isBottomBarVisible =  true
                isTopBarVisible = true
                SongsListScreen(
                    showSnackbar = { showSnackbar(it) }
                )
            }
        }
    }
}