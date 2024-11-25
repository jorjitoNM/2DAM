package com.example.apptareas.ui.event_details

import com.example.apptareas.domain.model.Event

sealed interface EventDetailsEvents {
    data class UpdateEvent (val userEvent : Event) : EventDetailsEvents
    data object EventDone : EventDetailsEvents
}