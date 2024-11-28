package com.example.apptareas.ui.event_details

import com.example.apptareas.domain.model.Event

sealed interface EventDetailsEvents {
    data class GetEvent (val eventId : Int) : EventDetailsEvents
    data class UpdateEvent (val event : Event) : EventDetailsEvents
    data object EventDone : EventDetailsEvents
}