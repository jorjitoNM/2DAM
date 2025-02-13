package com.example.hospitalroomcompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.hospitalroomcompose.data.common.LocalDateConverters
import com.example.hospitalroomcompose.data.common.MedicationsConverter
import com.example.hospitalroomcompose.data.local.dao.MedicalRecordsDao
import com.example.hospitalroomcompose.data.local.dao.MedicationsDao
import com.example.hospitalroomcompose.data.local.dao.PatientsDao
import com.example.hospitalroomcompose.data.local.dao.UserDao
import com.example.hospitalroomcompose.data.local.model.MedicalRecordEntity
import com.example.hospitalroomcompose.data.local.model.MedicationEntity
import com.example.hospitalroomcompose.data.local.model.PatientEntity
import com.example.hospitalroomcompose.data.local.model.PatientMedicalRecordsCrossRef
import com.example.hospitalroomcompose.data.local.model.UserEntity

@Database(
    entities = [UserEntity::class,PatientEntity::class,MedicationEntity::class,MedicalRecordEntity::class,PatientMedicalRecordsCrossRef::class],
    version = 3,
    exportSchema = true
)
@TypeConverters(LocalDateConverters::class, MedicationsConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun patientsDao() : PatientsDao
    abstract fun medicalRecordsDao() : MedicalRecordsDao
    abstract fun medicationsDao() : MedicationsDao
}