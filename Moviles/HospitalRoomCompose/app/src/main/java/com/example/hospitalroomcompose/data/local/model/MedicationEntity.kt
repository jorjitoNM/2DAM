package com.example.hospitalroomcompose.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medications")
data class MedicationEntity(
    @PrimaryKey(autoGenerate = true)
    val id :Int,
    val medicationName : String = "",
    val dosage : String = "",
)
