package com.example.apptareas.data.remote

import com.example.apptareas.data.remote.datasource.EventsDataSource
import com.example.apptareas.data.remote.model.events.EventRemote
import javax.inject.Inject

class EventsRepository @Inject constructor (
    private val eventsDataSource: EventsDataSource,
) {
    suspend fun getEvents(userId: Int) =
        eventsDataSource.getEvents(userId)


    suspend fun update(event: EventRemote) =
        eventsDataSource.updateEvent(event)

    suspend fun deleteEvent(event: EventRemote) {
        eventsDataSource.deleteEvent(event)
    }
}