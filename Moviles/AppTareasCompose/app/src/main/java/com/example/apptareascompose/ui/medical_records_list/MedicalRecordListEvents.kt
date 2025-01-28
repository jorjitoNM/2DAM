package com.example.apptareascompose.ui.medical_records_list

sealed interface MedicalRecordListEvents {
    data class GetAllMedicalRecord (val patientId : Int) : MedicalRecordListEvents
    data object EventDone : MedicalRecordListEvents
}