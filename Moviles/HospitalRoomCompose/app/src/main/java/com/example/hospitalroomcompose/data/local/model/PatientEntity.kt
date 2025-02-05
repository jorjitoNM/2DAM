package com.example.hospitalroomcompose.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "patients")
data class PatientEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val name : String = "",
    val birthDate : LocalDate = LocalDate.now(),
    val phone : String = "",
    val paid : Int = 0,
)
