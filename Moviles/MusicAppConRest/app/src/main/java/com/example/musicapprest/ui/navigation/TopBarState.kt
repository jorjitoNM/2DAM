package com.example.musicapprest.ui.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable

data class ScaffoldState(
    val topBarState: TopBarState = TopBarState(),
    val fabVisible : Boolean = false,
    val bottomBarVisible : Boolean = true,
)

data class TopBarState(
    val arrangement: Arrangement.Horizontal = Arrangement.Center,
    val actions : @Composable RowScope.() -> Unit = {},
    val showNavigationIcon : Boolean = true,
)