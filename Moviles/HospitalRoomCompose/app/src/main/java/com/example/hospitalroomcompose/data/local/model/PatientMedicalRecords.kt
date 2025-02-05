package com.example.hospitalroomcompose.data.local.model

import androidx.room.Embedded
import androidx.room.Relation

data class PatientMedicalRecords(
    @Embedded val patient: PatientEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "patientId"
    )
    val medicalRecords: List<MedicalRecordEntity>
)
