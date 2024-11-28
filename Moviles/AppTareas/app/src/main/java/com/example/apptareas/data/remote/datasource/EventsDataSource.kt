package com.example.apptareas.data.remote.datasource

import com.example.apptareas.data.remote.NetworkResult
import com.example.apptareas.data.remote.api_service.EventsService
import com.example.apptareas.data.remote.model.events.EventRemote
import com.example.apptareas.data.remote.model.events.toEvent
import com.example.apptareas.domain.model.Event
import javax.inject.Inject

class EventsDataSource @Inject constructor (
    private val eventsService : EventsService,
) : BaseApiResponse() {
    suspend fun getEvents () : NetworkResult<List<Event>> {
        return safeApiCall { eventsService.getEvents() }.map { event -> event.map { e -> e.toEvent() } }
    }

    suspend fun updateEvent (event : EventRemote) =
       safeApiCall { eventsService.updateEvent(event.id,event) }.map { updatedEvent -> updatedEvent.toEvent()}

    suspend fun deleteEvent (event : EventRemote) =
        safeApiCall { eventsService.deleteEvent(event.id) }
}