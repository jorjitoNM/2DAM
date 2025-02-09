package com.example.apptareascompose.ui.medical_record_details

import com.example.hospitalroomcompose.domain.model.MedicalRecord
import com.example.primeraapp.ui.common.UiEvent

data class MedicalRecordDetailsState (
    val medicalRecord : MedicalRecord = MedicalRecord(),
    val uiEvent : UiEvent? = null,
    val isLoading : Boolean = false,
)