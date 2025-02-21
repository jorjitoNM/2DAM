package com.example.examen2evajorgenovillo.ui.navigation


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.examen2evajorgenovillo.ui.common.Constantes

val appDestinationList = listOf(
    LoginScreen,
    AlumnosListScreen,
    InformesListScreen,
    RatonesListScreen,
    InformeDetailsScreen,
)

interface AppDestination{
    val route: Any
    val title: String
    val scaffoldState: ScaffoldState
        get() = ScaffoldState(
            topBarState = TopBarState(showNavigationIcon = true, arrangement = Arrangement.Start),
            fabVisible = true
        )
    val isBottomBarVisible : Boolean
    val isTopBarVisible : Boolean
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
    )
    override val isBottomBarVisible = false
    override val isTopBarVisible = false
}

object AlumnosListScreen : AppMainBottomDestination {
    override val onBottomBar: Boolean = true
    override val icon = Icons.AutoMirrored.Filled.List
    override val route = AlumnosListDestination
    override val title = Constantes.ALUMNOS_LIST
    override val scaffoldState = ScaffoldState(
        topBarState = TopBarState(showNavigationIcon = true, arrangement = Arrangement.Start),
        fabVisible = false,
    )
    override val isBottomBarVisible = true
    override val isTopBarVisible = true
}

object RatonesListScreen : AppMainBottomDestination {
    override val onBottomBar: Boolean = true
    override val icon = Icons.AutoMirrored.Filled.List
    override val route = RatonesListDestination
    override val title = Constantes.RATONES_LIST
    override val scaffoldState = ScaffoldState(
        topBarState = TopBarState(showNavigationIcon = true, arrangement = Arrangement.Start),
        fabVisible = false,
    )
    override val isBottomBarVisible = false
    override val isTopBarVisible = true
}

object InformesListScreen : AppMainBottomDestination {
    override val onBottomBar: Boolean = true
    override val icon = Icons.AutoMirrored.Filled.List
    override val route = InformesListDestination
    override val title = Constantes.INFORMES_LIST
    override val scaffoldState = ScaffoldState(
        topBarState = TopBarState(showNavigationIcon = true, arrangement = Arrangement.Start),
        fabVisible = false,
    )
    override val isBottomBarVisible = true
    override val isTopBarVisible = true
}

object InformeDetailsScreen : AppDestination {
    override val route = InformeDetailsDestination
    override val title = Constantes.INFORME_DETAILS
    override val scaffoldState = ScaffoldState(
        topBarState = TopBarState(showNavigationIcon = true, arrangement = Arrangement.Start),
        fabVisible = false,
    )
    override val isBottomBarVisible = true
    override val isTopBarVisible = true
}