package com.example.hospitalroomcompose.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.hospitalroomcompose.data.local.model.MedicalRecordEntity
import com.example.hospitalroomcompose.data.local.model.PatientMedicalRecords

@Dao
interface MedicalRecordsDao {
    @Query("SELECT * FROM medicalRecords WHERE patientId == :id")
    suspend fun getPatientMedicalRecords (id :Int) : PatientMedicalRecords

    @Query("SELECT * FROM medicalRecords WHERE id == :id")
    suspend fun getMedicalRecord (id :Int) : MedicalRecordEntity
}