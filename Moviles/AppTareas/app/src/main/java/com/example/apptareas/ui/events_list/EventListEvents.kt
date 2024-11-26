package com.example.apptareas.ui.events_list

import com.example.apptareas.domain.model.Event

sealed interface EventListEvents {
    data class GetEvents (val userId : Int) : EventListEvents
    data class DeleteEvent (val event : Event) : EventListEvents
    data object EventDone : EventListEvents
}