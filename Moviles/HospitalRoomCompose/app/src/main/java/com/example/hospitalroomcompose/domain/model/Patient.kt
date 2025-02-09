package com.example.apptareascompose.domain.model

import java.time.LocalDate

data class Patient (
    val id : Int = 0,
    val name : String = "",
    val birthDate : LocalDate = LocalDate.now(),
    val phone : String = "",
    val paid : Int = 0,
)