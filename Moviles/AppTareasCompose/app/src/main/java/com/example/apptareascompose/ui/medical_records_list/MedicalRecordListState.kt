package com.example.apptareascompose.ui.medical_records_list

import com.example.apptareascompose.domain.model.MedicalRecord
import com.example.primeraapp.ui.common.UiEvent

data class MedicalRecordListState(
    val medicalRecords : List<MedicalRecord> = emptyList(),
    val uiEvent: UiEvent? = null,
    val isLoading : Boolean = false,
)
