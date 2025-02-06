package com.example.hospitalroomcompose.ui.medications_list

sealed interface MedicationListEvents {
    data object GetAllMedications : MedicationListEvents
    data object EventDone : MedicationListEvents
}