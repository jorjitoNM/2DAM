package com.example.hospitalroomcompose.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "medicalRecords")
data class MedicalRecordEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val description : String = "",
    val date : LocalDate = LocalDate.now(),
    val patientId : Int = 0,
    val doctorId : Int = 0,
    val medications : List<String> = emptyList(),
)
