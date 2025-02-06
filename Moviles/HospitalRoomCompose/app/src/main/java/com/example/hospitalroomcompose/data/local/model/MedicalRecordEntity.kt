package com.example.hospitalroomcompose.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hospitalroomcompose.domain.model.MedicalRecord
import java.time.LocalDate

@Entity(tableName = "medicalRecords")
data class MedicalRecordEntity(
    @PrimaryKey(autoGenerate = true)
    val recordId : Int = 0,
    val description : String = "",
    val date : LocalDate = LocalDate.now(),
    val doctorId : Int = 0,
    val patientId : Int = 0,
    val medications : List<String> = emptyList(),
)

fun MedicalRecordEntity.toMedicalRecord() = MedicalRecord(recordId,description,date,doctorId,patientId,medications)
