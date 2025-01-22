package com.example.primeraapp.ui.loginActivity

sealed interface LoginEvent {
    data class registerUser(val username: String, val password: String) : LoginEvent
    data class validateUser(val username: String, val password: String) : LoginEvent
}