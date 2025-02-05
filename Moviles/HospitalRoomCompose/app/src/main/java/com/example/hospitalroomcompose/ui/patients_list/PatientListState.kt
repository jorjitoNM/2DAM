package com.example.apptareascompose.ui.patients_list

import com.example.apptareascompose.domain.model.Patient
import com.example.primeraapp.ui.common.UiEvent

data class PatientListState(
    val patients : List<Patient> = emptyList(),
    val isLoading : Boolean = false,
    val uiEvent : UiEvent? = null
)
