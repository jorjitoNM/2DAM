package com.example.hospitalroomcompose.ui.login

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hospitalroomcompose.domain.model.User
import com.example.hospitalroomcompose.ui.navigation.PatientsListScreenDestination
import com.example.hospitalroomcompose.ui.common.Constantes
import com.example.primeraapp.ui.common.UiEvent

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    showSnackbar: (String) -> Unit,
    navController: NavController,
) {
    val uiState by loginViewModel.uiState.collectAsState()
    val user = uiState.user
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
        user = user,
        onUsernameChange = { newUsername ->
            loginViewModel.handleEvent(LoginEvents.UpdateUsername(newUsername))
        },
        onPasswordChange = { newPassword ->
            loginViewModel.handleEvent(LoginEvents.UpdatePassword(newPassword))
        },
        onLoginClick = { loginViewModel.handleEvent(LoginEvents.Login(user)) },
        onRegisterClick = { loginViewModel.handleEvent(LoginEvents.Register(user)) },
    )
}

@Composable
fun LoginContent(
    user : User,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
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
                onValueChange = onUsernameChange,
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
                onValueChange = onPasswordChange,
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
                Button(onClick = onLoginClick) { Text(
                    Constantes.LOGIN) }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.5f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = onRegisterClick) { Text(
                    Constantes.SING_UP) }
            }
        }
        Spacer(modifier = Modifier.weight(0.1f))
    }
}

@Composable
@Preview
fun LoginScreenPreview () {
    var user by rememberSaveable { mutableStateOf(User()) }
    LoginContent (
        user = User(),
        onUsernameChange = { newUsername -> user = user.copy(username = newUsername) },
        onPasswordChange = { newPassword -> user = user.copy(password = newPassword) },
        onLoginClick = {  },
        onRegisterClick = { },
    )
}
