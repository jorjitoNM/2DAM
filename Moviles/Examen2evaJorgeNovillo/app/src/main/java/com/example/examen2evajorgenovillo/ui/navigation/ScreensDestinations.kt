package com.example.examen2evajorgenovillo.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object LoginScreenDestination

@Serializable
object AlumnosListDestination

@Serializable
object RatonesListDestination

@Serializable
object InformesListDestination

@Serializable
data class InformeDetailsDestination(val informeId : Int)