package com.example.apptareas.data.remote.datasource

import com.example.apptareas.R
import com.example.apptareas.data.remote.api_service.EventsService
import com.example.viewmodel.data.remote.NetworkResult

class EventsDataSource (
    private val eventsService : EventsService,
) : BaseApiResponse() {
    suspend fun getEvents (userId : Int) =
        safeApiCall { eventsService.getEvents(userId) }.then { events -> {
            if (events.isNotEmpty())
                events.map { e -> e.toEvent() }
            else
                NetworkResult.Error(R.string.get_events_failed.toString())
        } }
}