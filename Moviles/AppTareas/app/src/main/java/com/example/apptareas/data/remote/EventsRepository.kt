package com.example.apptareas.data.remote

import com.example.apptareas.data.remote.datasource.EventsDataSource
import com.example.apptareas.data.remote.model.events.EventRemote
import com.example.apptareas.domain.model.Event
import javax.inject.Inject

class EventsRepository @Inject constructor (
    private val eventsDataSource: EventsDataSource,
) {
    suspend fun getEvents() : NetworkResult<List<Event>> {
        return eventsDataSource.getEvents()
    }

    suspend fun update(event: EventRemote) =
        eventsDataSource.updateEvent(event)

    suspend fun deleteEvent(event: EventRemote) {
        eventsDataSource.deleteEvent(event)
    }

    suspend fun getEvent(eventId: Int) =
        eventsDataSource.getEvent(eventId)
}