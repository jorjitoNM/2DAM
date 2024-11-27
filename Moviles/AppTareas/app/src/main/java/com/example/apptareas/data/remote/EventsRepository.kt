package com.example.apptareas.data.remote

import com.example.apptareas.data.remote.datasource.EventsDataSource
import com.example.apptareas.data.remote.model.events.EventRemote
import com.example.apptareas.domain.model.Event
import javax.inject.Inject

class EventsRepository @Inject constructor (
    private val eventsDataSource: EventsDataSource,
) {
    suspend fun getEvents(userId: Int) : NetworkResult<Event> {
        return eventsDataSource.getEvents(userId)
    }

    suspend fun update(event: EventRemote) =
        eventsDataSource.updateEvent(event)

    suspend fun deleteEvent(event: EventRemote) {
        eventsDataSource.deleteEvent(event)
    }
}