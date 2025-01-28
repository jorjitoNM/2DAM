package com.example.apptareascompose.ui.navigation

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.apptareascompose.ui.login.LoginScreen
import com.example.apptareascompose.ui.medical_records_list.MedicalRecordListScreen
import com.example.apptareascompose.ui.patients_list.PatientsListScreen
import kotlinx.coroutines.launch

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val showSnackbar = { message: String, showUndo: Boolean, undo: () -> Unit ->
        scope.launch {
            if (showUndo) {
                val result = snackbarHostState.showSnackbar(
                    message,
                    actionLabel = "UNDO",
                    duration = SnackbarDuration.Short
                )
                if (result == SnackbarResult.ActionPerformed) {
                    undo()
                }
            } else {
                snackbarHostState.showSnackbar(
                    message,
                    duration = SnackbarDuration.Short
                )
            }
        }
    }
    NavHost(
        navController = navController,
        startDestination = LoginScreen,
    ) {
        //Login(showSnackbar("",{ mensaje -> {}} ))
        composable<PatientsListScreen> {
            PatientsListScreen(
                showSnackbar = { mensaje, undo -> },
                onNavigateDetail = { patientId ->
                    navController.navigate(MedicalRecordListScreen(patientId.toInt()))
                }
            )
        }
        composable<MedicalRecordListScreen> {
            MedicalRecordListScreen(
                patientId = (it.toRoute() as MedicalRecordListScreen).patientId,
                showSnackbar = { mensaje, undo -> },
                onNavigateDetalle = { medicalRecordId ->
                    navController.navigate(MedicalRecordDetail(medicalRecordId.toInt()))
                }
            )
        }
        composable<MedicalRecordDetail> {

        }
    }
}

fun NavGraphBuilder.Login(
    showSnackbar: ((String), () -> Unit) -> Unit = { mensaje, func -> {} }
) {
    composable<LoginScreen>(
    ) {
        LoginScreen(showSnackbar = showSnackbar)
    }
}