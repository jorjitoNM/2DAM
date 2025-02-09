package com.example.apptareascompose.ui.doctors_list

sealed interface DoctorListEvents {
    data object GetAllDoctors : DoctorListEvents
    data object EventDone : DoctorListEvents
}