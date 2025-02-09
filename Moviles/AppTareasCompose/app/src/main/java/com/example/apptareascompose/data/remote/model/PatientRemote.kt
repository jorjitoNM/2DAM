package com.example.apptareascompose.data.remote.model

import com.example.apptareascompose.domain.model.Patient
import java.time.LocalDate

data class PatientRemote(
    val id : Int = 0,
    val name : String = "",
    val birthDate : String = LocalDate.now().toString(),
    val phone : String = "",
    val paid : Int = 0,
)

fun PatientRemote.toPatient() : Patient {
    return Patient(id,name,LocalDate.parse(birthDate),phone,paid)
}
