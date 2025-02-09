package com.example.apptareascompose.ui.medical_record_details

sealed interface MedicalRecordDetailsEvents {
    data class GetMedicalRecords (val recordId : Int) : MedicalRecordDetailsEvents
    data object EventDone : MedicalRecordDetailsEvents
}