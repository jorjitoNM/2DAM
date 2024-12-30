package com.example.apptareas.domain.usecases.events_usercases

import com.example.apptareas.data.EventsRepository
import javax.inject.Inject

class GetEventsUseCase @Inject constructor(private val eventsRepository: EventsRepository) {
    operator fun invoke () = eventsRepository.getEvents()
}