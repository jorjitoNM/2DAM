package com.example.hospitalroomcompose.data

import com.example.hospitalroomcompose.data.local.dao.MedicalRecordsDao
import javax.inject.Inject

class MedicalRecordsRepository @Inject constructor(
    private val medicalRecordsDao: MedicalRecordsDao
) {
    suspend fun getPatientMedicalRecords (id :Int) = medicalRecordsDao.getPatientMedicalRecords(id)

    suspend fun getMedicalRecord (id : Int) = medicalRecordsDao.getMedicalRecord(id)
}