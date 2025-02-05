package com.example.apptareascompose.domain.model

import java.time.LocalDate

data class MedicalRecord(
    val id : Int = 0,
    val description : String = "",
    val date : LocalDate = LocalDate.now(),
    val patientId : Int = 0,
    val doctorId : Int = 0,
    val medications : List<String> = emptyList(),
)
