package com.example.apptareas.domain.usecases

import com.example.apptareas.data.remote.EventsRepository
import com.example.apptareas.data.remote.NetworkResult
import javax.inject.Inject

class FilterEventsUseCase @Inject constructor(private val eventsRepository: EventsRepository) {
    suspend operator fun invoke (eventName:  String) =
        eventsRepository.getEvents().then { events -> NetworkResult.Success(events.filter { e -> e.body.contains(eventName) }) }
}