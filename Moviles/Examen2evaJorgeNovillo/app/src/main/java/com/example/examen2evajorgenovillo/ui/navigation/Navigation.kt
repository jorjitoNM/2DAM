package com.example.examen2evajorgenovillo.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.examen2evajorgenovillo.ui.alumnos_list.AlumnosListScreen
import com.example.examen2evajorgenovillo.ui.common.BottomBar
import com.example.examen2evajorgenovillo.ui.common.TopBar
import com.example.examen2evajorgenovillo.ui.informes_details.InformeDetailsScreen
import com.example.examen2evajorgenovillo.ui.informes_list.InformesListScreen
import com.example.examen2evajorgenovillo.ui.login.LoginScreen
import com.example.examen2evajorgenovillo.ui.ratones_list.RatonesListScreen
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

    val screen = appDestinationList.find { screen ->
        val currentRoute = state?.destination?.route?.substringBefore("/")
        val screenRoute = screen.route.toString().substringBefore("@").substringBefore("$")
        currentRoute == screenRoute
    }

    val bottomBar: @Composable () -> Unit = {
        BottomBar(
            navController = navController,
            screens = appDestinationList,
            isVisible = screen?.isBottomBarVisible ?: false
        )
    }
    val topBar: @Composable () -> Unit = {
        TopBar(
            navController = navController,
            screen = screen,
            isVisible = screen?.isTopBarVisible ?: true,
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
                LoginScreen(
                    showSnackbar = { showSnackbar(it) },
                    navigateToApp = { navController.navigate(AlumnosListDestination)},
                )
            }
            composable<AlumnosListDestination> {
                AlumnosListScreen(
                    showSnackbar = { showSnackbar(it)},
                    navigateToLogin = { navController.navigate(LoginScreenDestination)}
                )
            }
            composable<InformesListDestination> {
                InformesListScreen(
                    showSnackbar = { showSnackbar(it)},
                    navigateToLogin = { navController.navigate(LoginScreenDestination)}
                )
            }
            composable<RatonesListDestination> {
                RatonesListScreen(
                    showSnackbar = { showSnackbar(it)},
                    navigateToLogin = { navController.navigate(LoginScreenDestination)}
                )
            }
            composable<InformeDetailsDestination> {
                InformeDetailsScreen(
                    showSnackbar = { showSnackbar(it)},
                    navigateToLogin = { navController.navigate(LoginScreenDestination)}
                )
            }
        }
    }
}