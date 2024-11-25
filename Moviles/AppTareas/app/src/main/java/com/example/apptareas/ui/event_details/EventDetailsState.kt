package com.example.apptareas.ui.event_details

import com.example.apptareas.domain.model.Event
import com.example.apptareas.ui.common.UiEvent

class EventDetailsState (
    val event : Event = Event(),
    val appEvent : UiEvent? = null,
)