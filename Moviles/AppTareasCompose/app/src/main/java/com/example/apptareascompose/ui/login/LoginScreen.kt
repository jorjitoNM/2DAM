package com.example.apptareascompose.ui.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LoginScreen (
    loginViewModel: LoginViewModel = hiltViewModel(),
    showSnackbar: (String, () -> Unit) -> Unit,
) {
    val uiState by loginViewModel.uiState.collectAsState()

    LoginContent (

    )
}

@Composable
fun LoginContent() {
    Box(

    ) {
        Row {
//            TextField(
//                value = "",
//                onValueChange = ,
//                placeholder = "Username (ej. juanElOne)",
//            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview (modifier: Modifier = Modifier) {
    LoginContent()
}
