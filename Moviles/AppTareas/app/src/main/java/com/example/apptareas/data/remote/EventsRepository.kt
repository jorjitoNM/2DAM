package com.example.apptareas.data.remote

import com.example.apptareas.data.remote.datasource.EventsDataSource
import com.example.apptareas.data.remote.model.events.EventRemote
import com.example.apptareas.domain.model.Event

class EventsRepository (
    private val eventsDataSource: EventsDataSource,
) {
    suspend fun getEvents(userId: Int): NetworkResult<List<Event>> =
        eventsDataSource.getEvents(userId)


    suspend fun update(event: EventRemote) : NetworkResult<Event> =
        eventsDataSource.updateEvent(event)
}