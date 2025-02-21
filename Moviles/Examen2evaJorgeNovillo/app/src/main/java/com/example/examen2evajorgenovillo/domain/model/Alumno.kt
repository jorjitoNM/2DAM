package com.example.examen2evajorgenovillo.domain.model

data class Alumno(
    val nombre : String = "",
    val apellido : String = "",
    val dni : String = "",
    val email : String = "",
    val asignaturas : List<Asignatura>
)
