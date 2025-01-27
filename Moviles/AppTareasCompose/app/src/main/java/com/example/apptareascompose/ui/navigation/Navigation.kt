package com.example.apptareascompose.ui.navigation

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

@Composable
fun Navigation () {
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val showSnackbar = { message:String,showUndo: Boolean,undo:()->Unit  ->
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
            }
            else {
                snackbarHostState.showSnackbar(
                    message,
                    duration = SnackbarDuration.Short
                )
            }
        }
    }
}