package com.example.hospitalroomcompose.data.local.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class PatientWithMedicalRecords(
    @Embedded val patient: PatientEntity,
    @Relation(
        parentColumn = "patientId",
        entityColumn = "recordId",
        associateBy = Junction(PatientMedicalRecordsCrossRef::class)
    )
    val medicalRecords : List<MedicalRecordEntity>
)
