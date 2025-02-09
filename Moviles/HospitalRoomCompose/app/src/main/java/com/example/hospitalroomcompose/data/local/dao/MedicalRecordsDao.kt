package com.example.hospitalroomcompose.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.hospitalroomcompose.data.local.model.MedicalRecordEntity
import com.example.hospitalroomcompose.data.local.model.PatientWithMedicalRecords

@Dao
interface MedicalRecordsDao {
    @Query("SELECT * FROM patients WHERE patientId == :id")
    suspend fun getPatientMedicalRecords (id :Int) : List<PatientWithMedicalRecords>

    @Query("SELECT * FROM medicalRecords WHERE recordId == :id")
    suspend fun getMedicalRecord (id :Int) : MedicalRecordEntity
}