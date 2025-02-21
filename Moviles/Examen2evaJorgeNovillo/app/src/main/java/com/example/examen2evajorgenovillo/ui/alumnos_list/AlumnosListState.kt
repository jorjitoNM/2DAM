package com.example.examen2evajorgenovillo.ui.alumnos_list

import com.example.examen2evajorgenovillo.domain.model.Alumno
import com.example.examen2evajorgenovillo.ui.common.UiEvent

data class AlumnosListState(
    val alumnos : List<Alumno> = emptyList(),
    val event : UiEvent? = null,
    val isLoading : Boolean = false,
)
