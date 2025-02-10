package com.example.hospitalroomcompose.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.apptareascompose.domain.model.Medication

@Entity(tableName = "medications")
data class MedicationEntity(
    @PrimaryKey(autoGenerate = false)
    val id :Int,
    val medicationName : String = "",
    val dosage : String = "",
)

fun MedicationEntity.toMedication() = Medication(id,medicationName,0,dosage)
