package com.example.hospitalroomcompose.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.apptareascompose.domain.model.Patient
import java.time.LocalDate

@Entity(tableName = "patients")
data class PatientEntity(
    @PrimaryKey(autoGenerate = true)
    val patientId : Int = 0,
    val name : String = "",
    val birthDate : LocalDate = LocalDate.now(),
    val phone : String = "",
    val paid : Int = 0,
)

fun PatientEntity.toPatient() = Patient(patientId,name,birthDate,phone,paid)
