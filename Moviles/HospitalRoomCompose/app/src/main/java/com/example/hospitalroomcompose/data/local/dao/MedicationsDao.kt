package com.example.hospitalroomcompose.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.hospitalroomcompose.data.local.model.MedicationEntity

@Dao
interface MedicationsDao {

    @Query("SELECT * FROM medications")
    suspend fun getAllMedications() : List<MedicationEntity>
}