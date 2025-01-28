package com.example.apptareascompose.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object PatientsListScreen

@Serializable
data class MedicalRecordListScreen(val patientId : Int)

@Serializable
data class MedicalRecordDetail(val medicalRecordId : Int)

@Serializable
object LoginScreen
