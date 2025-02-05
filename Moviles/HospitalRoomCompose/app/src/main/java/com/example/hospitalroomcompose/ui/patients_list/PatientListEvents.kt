package com.example.apptareascompose.ui.patients_list

sealed interface PatientListEvents {
    data object GetAllPatients: PatientListEvents
    data object EventDone : PatientListEvents
}