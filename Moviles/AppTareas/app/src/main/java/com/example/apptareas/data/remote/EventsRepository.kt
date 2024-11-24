package com.example.apptareas.data.remote

import com.example.apptareas.data.remote.datasource.EventsDataSource
import com.example.apptareas.domain.model.Event
import com.example.viewmodel.data.remote.NetworkResult

class EventsRepository (
    private val eventsDataSource: EventsDataSource,
) {
    suspend fun getEvents(userId: Int): NetworkResult<List<Event>> =
        eventsDataSource.getEvents(userId)
}