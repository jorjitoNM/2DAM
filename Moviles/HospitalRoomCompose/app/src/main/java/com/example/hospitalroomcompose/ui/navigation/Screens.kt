package com.example.hospitalroomcompose.ui.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.apptareascompose.ui.navigation.LoginScreenDestination
import com.example.apptareascompose.ui.navigation.MedicalRecordListScreenDestination
import com.example.apptareascompose.ui.navigation.MedicationsListDestination
import com.example.apptareascompose.ui.navigation.PatientsListScreenDestination
import com.example.apptareascompose.ui.navigation.ScaffoldState
import com.example.apptareascompose.ui.navigation.TopBarState
import com.example.hospitalroomcompose.data.common.Constantes

val appDestinationList = listOf(
    PatientsListScreen,
    MedicalRecordsListScreen,
    MedicalRecordDetailsScreen,
    LoginScreen,
    MedicationsListScreen
)

interface AppDestination{
    val route: Any
    val title: String
    val scaffoldState: ScaffoldState
        get() = ScaffoldState(
            topBarState = TopBarState(showNavigationIcon = false, arrangement = Arrangement.Start),
            fabVisible = true
        )
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
}

object MedicalRecordsListScreen : AppDestination {
    override val route = MedicalRecordListScreenDestination
    override val title = Constantes.MEDICAL_RECORDS
}

object MedicalRecordDetailsScreen : AppDestination {
    override val route = MedicalRecordListScreenDestination
    override val title = Constantes.MEDICAL_RECORDS
}

object LoginScreen : AppDestination {
    override val route = LoginScreenDestination
    override val title = Constantes.LOGIN
}

object MedicationsListScreen : AppMainBottomDestination {
    override val route = MedicationsListDestination
    override val title = Constantes.MEDICATIONS
    override val onBottomBar = true
    override val icon = Icons.AutoMirrored.Filled.List
}