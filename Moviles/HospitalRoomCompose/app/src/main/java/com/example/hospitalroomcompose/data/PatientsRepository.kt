package com.example.hospitalroomcompose.data

import com.example.hospitalroomcompose.data.local.dao.PatientsDao
import javax.inject.Inject

class PatientsRepository @Inject constructor(
    private val patientsDao: PatientsDao,
) {
    suspend fun getAllPatients () = patientsDao.getAllPatients()
}