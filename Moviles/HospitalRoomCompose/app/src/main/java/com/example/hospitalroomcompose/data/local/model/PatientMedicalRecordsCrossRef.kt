package com.example.hospitalroomcompose.data.local.model

import androidx.room.Entity

@Entity(primaryKeys = ["patientId", "recordId"])
data class PatientMedicalRecordsCrossRef(
    val patientId: Int,
    val recordId: Int,
)
