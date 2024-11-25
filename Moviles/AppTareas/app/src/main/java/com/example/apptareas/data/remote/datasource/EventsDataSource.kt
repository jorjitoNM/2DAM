package com.example.apptareas.data.remote.datasource

import com.example.apptareas.R
import com.example.apptareas.data.remote.api_service.EventsService
import com.example.apptareas.data.remote.model.events.EventRemote
import com.example.apptareas.domain.model.Event
import com.example.apptareas.data.remote.NetworkResult

class EventsDataSource (
    private val eventsService : EventsService,
) : BaseApiResponse() {
    suspend fun getEvents (userId : Int) =
        safeApiCall { eventsService.getEvents(userId) }.map { events -> events.toEvent() }

    suspend fun updateEvent (event : EventRemote) =
        safeApiCall { eventsService.updateEvent(event.id,event) }.map { updatedEvent -> updatedEvent.toEvent()}
}