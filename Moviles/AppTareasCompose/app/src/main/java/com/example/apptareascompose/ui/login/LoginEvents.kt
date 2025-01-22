package com.example.apptareascompose.ui.login

sealed interface LoginEvents {
    data class login (val username : String, val password : String) : LoginEvents
    data class register (val username : String, val password : String) : LoginEvents
}