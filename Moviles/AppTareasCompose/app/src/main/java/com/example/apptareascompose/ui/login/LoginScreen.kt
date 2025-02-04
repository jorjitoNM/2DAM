package com.example.apptareascompose.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.apptareascompose.domain.model.User
import com.example.apptareascompose.ui.navigation.PatientsListScreenDestination
import com.example.primeraapp.ui.common.Constantes
import com.example.primeraapp.ui.common.UiEvent

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    showSnackbar: (String) -> Unit,
    navController: NavController,
) {
    val uiState by loginViewModel.uiState.collectAsState()

    LaunchedEffect(uiState.uiEvent) {
        uiState.uiEvent?.let {
            if (it is UiEvent.ShowSnackbar) {
                showSnackbar(it.message)
            }
            loginViewModel.handleEvent(LoginEvents.EventDone)
        }
    }

    LaunchedEffect(uiState.validated) {
        if (uiState.validated)
            navController.navigate(PatientsListScreenDestination)
    }

    LoginContent(
        loginViewModel = loginViewModel,
        user = uiState.user,
    )
}

@Composable
fun LoginContent(
    loginViewModel: LoginViewModel,
    user : User,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.weight(0.15f))
        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.25f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            TextField(
                value = user.username,
                onValueChange = { user.username = it },
                label = { Text(Constantes.USERNAME) },
                singleLine = true
            )
        }
        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.25f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            TextField(
                value = user.password,
                onValueChange = { user.password = it },
                label = { Text(Constantes.PASSWORD) },
                singleLine = true
            )
        }
        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.25f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.5f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = { loginViewModel.handleEvent(LoginEvents.Login(user)) }) { Text(Constantes.LOGIN) }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.5f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = { loginViewModel.handleEvent(LoginEvents.Register(user)) }) { Text(Constantes.SING_UP) }
            }
        }
        Spacer(modifier = Modifier.weight(0.1f))
    }
}
