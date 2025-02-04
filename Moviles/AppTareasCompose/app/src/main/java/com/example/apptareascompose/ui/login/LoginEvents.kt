package com.example.apptareascompose.ui.login

import com.example.apptareascompose.domain.model.User

sealed interface LoginEvents {
    data class Login (val user : User) : LoginEvents
    data class Register (val user : User) : LoginEvents
    data object EventDone : LoginEvents
}