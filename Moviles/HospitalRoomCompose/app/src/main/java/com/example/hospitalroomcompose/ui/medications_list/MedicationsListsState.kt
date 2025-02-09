package com.example.hospitalroomcompose.ui.medications_list

import com.example.apptareascompose.domain.model.Medication
import com.example.primeraapp.ui.common.UiEvent

data class MedicationsListsState(
    val medications : List<Medication> = emptyList(),
    val uiEvent : UiEvent? = null,
)
