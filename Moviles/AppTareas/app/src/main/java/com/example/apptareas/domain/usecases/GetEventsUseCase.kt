package com.example.apptareas.domain.usecases

import com.example.apptareas.data.remote.EventsRepository
import javax.inject.Inject

class GetEventsUseCase @Inject constructor(private val eventsRepository: EventsRepository) {
    suspend operator fun invoke (userId : Int) =
        eventsRepository.getEvents(userId)
}