package com.example.apptareas.ui.login

import androidx.lifecycle.ViewModel
import com.example.apptareas.domain.usecases.LogInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class LogInViewModel (
    private val logInUseCase : LogInUseCase,
) : ViewModel() {

}