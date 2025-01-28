package com.example.apptareascompose.ui.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.apptareascompose.data.utils.Constantes

val appDestinationList = listOf(PatientsListScreen,MedicalRecordsListScreen,MedicalRecordDetailsScreen,LoginScreen)

interface AppDestination{
    val route: Any
    val title: String
    val scaffoldState: ScaffoldState
        get() = ScaffoldState()
}

interface AppMainBottomDestination : AppDestination {
    val onBottomBar: Boolean
    val icon: ImageVector
}

object PatientsListScreen : AppMainBottomDestination {
    override val route = PatientsListScreenDestination
    override val title = Constantes.PATIENTS
    override val onBottomBar = true
    override val icon = Icons.AutoMirrored.Filled.List
    override val scaffoldState = ScaffoldState(
        topBarState =TopBarState(showNavigationIcon = false, arrangement = Arrangement.Start),
        fabVisible = true)
}

object MedicalRecordsListScreen : AppDestination {
    override val route = MedicalRecordListScreenDestination
    override val title = Constantes.MEDICAL_RECORDS
    override val scaffoldState = ScaffoldState(
        topBarState =TopBarState(showNavigationIcon = false, arrangement = Arrangement.Start),
        fabVisible = true)
}

object MedicalRecordDetailsScreen : AppDestination {
    override val route = MedicalRecordListScreenDestination
    override val title = Constantes.MEDICAL_RECORDS
    override val scaffoldState = ScaffoldState(
        topBarState =TopBarState(showNavigationIcon = false, arrangement = Arrangement.Start),
        fabVisible = true)
}

object LoginScreen : AppDestination {
    override val route = LoginScreenDestination
    override val title = Constantes.LOGIN
    override val scaffoldState = ScaffoldState(
        topBarState =TopBarState(showNavigationIcon = false, arrangement = Arrangement.Start),
        fabVisible = true)
}