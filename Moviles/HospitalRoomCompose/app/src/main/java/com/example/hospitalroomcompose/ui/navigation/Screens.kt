package com.example.hospitalroomcompose.ui.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.hospitalroomcompose.data.common.Constantes

val appDestinationList = listOf(
    LoginScreen,
    PatientsListScreen,
    MedicalRecordsListScreen,
    MedicalRecordDetailsScreen,
    MedicationsListScreen,
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
    override val scaffoldState = ScaffoldState(
        topBarState = TopBarState(showNavigationIcon = false, arrangement = Arrangement.Start),
        fabVisible = true,
        bottomBarVisible = false,
    )
}

object LoginScreen : AppDestination {
    override val route = LoginScreenDestination
    override val title = Constantes.LOGIN
    override val scaffoldState = ScaffoldState(
        topBarState = TopBarState(showNavigationIcon = false, arrangement = Arrangement.Start),
        fabVisible = true,
        bottomBarVisible = false,
    )
}

object MedicationsListScreen : AppMainBottomDestination {
    override val route = MedicationsListDestination
    override val title = Constantes.MEDICATIONS
    override val onBottomBar = true
    override val icon = Icons.AutoMirrored.Filled.List
}