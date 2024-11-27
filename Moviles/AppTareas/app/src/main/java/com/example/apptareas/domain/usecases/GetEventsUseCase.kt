package com.example.apptareas.domain.usecases

import com.example.apptareas.data.remote.EventsRepository
import com.example.apptareas.data.remote.NetworkResult
import com.example.apptareas.domain.model.Event
import javax.inject.Inject

class GetEventsUseCase @Inject constructor(private val eventsRepository: EventsRepository) {
    suspend operator fun invoke (userId : Int) : NetworkResult<Event> {
        return eventsRepository.getEvents(userId)
    }
}