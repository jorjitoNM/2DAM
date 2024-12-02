package com.example.examenjorgenovillo.ui.momentos_list

import com.example.examenjorgenovillo.domain.model.Momento
import com.example.examenjorgenovillo.ui.common.UiEvent

data class MomentosListState(
    val momentos : List<Momento> = emptyList(),
    val event : UiEvent? = null,
)
