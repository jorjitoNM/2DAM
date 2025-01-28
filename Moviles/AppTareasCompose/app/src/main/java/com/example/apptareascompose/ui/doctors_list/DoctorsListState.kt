package com.example.apptareascompose.ui.doctors_list

import com.example.apptareascompose.domain.model.Doctor
import com.example.primeraapp.ui.common.UiEvent

data class DoctorsListState(
    val doctors : List<Doctor>,
    val uiEvent: UiEvent? = null,
)
