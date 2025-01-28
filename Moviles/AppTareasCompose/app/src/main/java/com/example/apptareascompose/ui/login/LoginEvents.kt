package com.example.apptareascompose.ui.login

sealed interface LoginEvents {
    data class Login (val username : String, val password : String) : LoginEvents
    data class Register (val username : String, val password : String) : LoginEvents
    data object EventDone : LoginEvents
}