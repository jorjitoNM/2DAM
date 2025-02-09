package com.example.hospitalroomcompose.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.hospitalroomcompose.data.local.model.PatientEntity

@Dao
interface PatientsDao {
    @Query("SELECT * FROM patients")
    suspend fun getAllPatients () : List<PatientEntity>
}