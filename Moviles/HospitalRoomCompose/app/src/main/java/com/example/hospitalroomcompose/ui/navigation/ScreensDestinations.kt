package com.example.hospitalroomcompose.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object PatientsListScreenDestination

@Serializable
data class MedicalRecordListScreenDestination(val patientId : Int)

@Serializable
data class MedicalRecordDetailDestination(val medicalRecordId : Int)

@Serializable
object LoginScreenDestination

@Serializable
object MedicationsListDestination
