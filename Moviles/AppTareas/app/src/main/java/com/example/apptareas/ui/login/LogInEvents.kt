package com.example.apptareas.ui.login

import com.example.apptareas.domain.model.User

sealed interface LogInEvents {
    data class LogIn (val user : User) : LogInEvents
    data object ShowEvent : LogInEvents
}