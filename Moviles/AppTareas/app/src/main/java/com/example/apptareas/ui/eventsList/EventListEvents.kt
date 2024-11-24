package com.example.apptareas.ui.eventsList

interface EventListEvents {
    data class GetEvents (val userId : Int) : EventListEvents
    data object EventDone : EventListEvents
}