package com.example.apptareascompose.data.remote.model

import com.example.apptareascompose.domain.model.MedicalRecord
import com.example.apptareascompose.domain.model.Medication
import java.time.LocalDate

data class MedicalRecordRemote (
    val id : Int = 0,
    val description : String = "",
    val date : String = LocalDate.now().toString(),
    val patientId : Int = 0,
    val doctorId : Int = 0,
    val medications : List<String> = emptyList(),
)
fun MedicalRecordRemote.toMedicalRecord () : MedicalRecord =
    MedicalRecord(id,description,LocalDate.parse(date),patientId,doctorId,medications)