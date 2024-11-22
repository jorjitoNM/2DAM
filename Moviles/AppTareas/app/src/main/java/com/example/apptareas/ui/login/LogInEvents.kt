package com.example.apptareas.ui.login

import com.example.apptareas.domain.model.User

interface LogInEvents {
    data class LogIn (val user : User) : LogInEvents
    data object ShowEvent : LogInEvents
}