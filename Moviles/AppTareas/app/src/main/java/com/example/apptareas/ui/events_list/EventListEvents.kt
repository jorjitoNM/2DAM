package com.example.apptareas.ui.events_list

import com.example.apptareas.domain.model.Event

sealed interface EventListEvents {
    data object GetEvents : EventListEvents
    data class DeleteEvent (val event : Event) : EventListEvents
    data object EventDone : EventListEvents
}