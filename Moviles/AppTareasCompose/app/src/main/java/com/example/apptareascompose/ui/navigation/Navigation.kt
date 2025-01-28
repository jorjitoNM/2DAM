package com.example.apptareascompose.ui.navigation

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
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.apptareascompose.ui.common.BottomBar
import com.example.apptareascompose.ui.common.TopBar
import com.example.apptareascompose.ui.login.LoginScreen
import com.example.apptareascompose.ui.medical_records_list.MedicalRecordListScreen
import com.example.apptareascompose.ui.patients_list.PatientsListScreen
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
        state?.destination?.route == screen.route
    }

    val bottomBar: @Composable () -> Unit = {
        BottomBar(
            navController = navController,
            screens = appDestinationList
        )
    }
    val topBar: @Composable () -> Unit = {
        TopBar(
            navController = navController,
            screen = screen
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
            login(showSnackbar = {showSnackbar(it)})
            composable<PatientsListScreenDestination> {
                PatientsListScreen(
                    showSnackbar = { showSnackbar(it) },
                    onNavigateDetail = { patientId ->
                        navController.navigate(MedicalRecordListScreenDestination(patientId.toInt()))
                    }
                )
            }
            composable<MedicalRecordListScreenDestination> {
                MedicalRecordListScreen(
                    patientId = (it.toRoute() as MedicalRecordListScreenDestination).patientId,
                    showSnackbar = { showSnackbar(it) },
                    onNavigateDetalle = { medicalRecordId ->
                        navController.navigate(MedicalRecordDetailDestination(medicalRecordId.toInt()))
                    }
                )
            }
            composable<MedicalRecordDetailDestination> {

            }
        }
    }
}

fun NavGraphBuilder.login(
    showSnackbar: (String) -> Unit = { _ -> {} }
) {
    composable<LoginScreenDestination>(
    ) {
        LoginScreen(showSnackbar = showSnackbar)
    }
}