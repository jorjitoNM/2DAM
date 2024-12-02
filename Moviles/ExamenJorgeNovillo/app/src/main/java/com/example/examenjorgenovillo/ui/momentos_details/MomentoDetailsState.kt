package com.example.examenjorgenovillo.ui.momentos_details

import com.example.examenjorgenovillo.domain.model.Momento
import com.example.examenjorgenovillo.ui.common.UiEvent

data class MomentoDetailsState(
    val momento : Momento,
    val event : UiEvent? = null,
)
