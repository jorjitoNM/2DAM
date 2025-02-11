package com.example.hospitalroomcompose.data

import com.example.hospitalroomcompose.data.local.dao.MedicationsDao
import javax.inject.Inject

class MedicationsRepository @Inject constructor(
    private val medicationsDao: MedicationsDao,
) {
    suspend fun getAllMedications () = medicationsDao.getAllMedications()
}