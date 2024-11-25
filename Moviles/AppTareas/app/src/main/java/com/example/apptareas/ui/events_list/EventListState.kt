package com.example.apptareas.ui.events_list

import com.example.apptareas.domain.model.Event
import com.example.apptareas.ui.common.UiEvent

class EventListState (
    val events : List<Event> = emptyList(),
    val appEvent : UiEvent? = null,
)