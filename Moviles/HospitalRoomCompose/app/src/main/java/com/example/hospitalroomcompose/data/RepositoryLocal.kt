package com.example.hospitalroomcompose.data

import com.example.hospitalroomcompose.data.local.dao.MedicalRecordsDao
import com.example.hospitalroomcompose.data.local.dao.PatientsDao
import com.example.hospitalroomcompose.data.local.dao.UserDao
import com.example.primeraapp.data.local.modelo.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepositoryLocal @Inject constructor(
    private val userDao: UserDao,
    private val patientsDao: PatientsDao,
    private val medicalRecordsDao: MedicalRecordsDao,
) {

    suspend fun registerUser(username: String, password: String): Result<Unit> {
        return try {
            val hashedPassword = password.hashCode().toString()
            val user = UserEntity(username = username, password = hashedPassword)
            userDao.insertUser(user)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun login(username: String, password: String): Flow<Result<UserEntity?>> {
        val hashedPassword = password.hashCode().toString()
        return userDao.login(username, hashedPassword)
            .map { user -> Result.success(user) }
            .catch { e -> emit(Result.failure(e)) }
    }

    suspend fun getAllPatients () = patientsDao.getAllPatients()

    suspend fun getPatientMedicalRecords (id :Int) = medicalRecordsDao.getPatientMedicalRecords(id)

    suspend fun getMedicalRecord (id : Int) = medicalRecordsDao.getMedicalRecord(id)


}
