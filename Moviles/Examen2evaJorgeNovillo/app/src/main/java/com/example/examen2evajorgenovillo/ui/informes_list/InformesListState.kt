package com.example.examen2evajorgenovillo.ui.informes_list

import com.example.examen2evajorgenovillo.domain.model.Informe
import com.example.examen2evajorgenovillo.ui.common.UiEvent

data class InformesListState(
    val informes : List<Informe> = emptyList(),
    val event : UiEvent? = null,
)
