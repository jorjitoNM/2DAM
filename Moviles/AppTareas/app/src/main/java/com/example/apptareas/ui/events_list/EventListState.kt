package com.example.apptareas.ui.events_list

import com.example.apptareas.domain.model.Event
import com.example.apptareas.ui.common.UiEvent

data class EventListState (
    val events : List<Event> = emptyList(),
    val filteredEvents : List<Event> = emptyList(),
    val filtered : Boolean = false,
    val appEvent : UiEvent? = null,
)