package com.example.hospitalroomcompose.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.hospitalroomcompose.data.local.model.PatientEntity
import com.example.hospitalroomcompose.data.local.model.PatientWithMedicalRecords

@Dao
interface PatientsDao {
    @Query("SELECT * FROM patients")
    suspend fun getAllPatients () : List<PatientEntity>

    @Transaction
    @Query("SELECT * FROM patients WHERE patientId == :id")
    suspend fun getPatientMedicalRecords(id : Int) : List<PatientWithMedicalRecords>
}