package com.example.apptareas.ui.events_list

sealed interface EventListEvents {
    data class GetEvents (val userId : Int) : EventListEvents
    data object EventDone : EventListEvents
}