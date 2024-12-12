package com.example.apptareas.domain.usecases.events_usercases

import com.example.apptareas.data.remote.EventsRepository
import com.example.apptareas.data.remote.NetworkResult
import com.example.apptareas.domain.model.Event
import javax.inject.Inject

class GetEventsUseCase @Inject constructor(private val eventsRepository: EventsRepository) {
    suspend operator fun invoke () : NetworkResult<List<Event>> {
        return eventsRepository.getEvents()
    }
}