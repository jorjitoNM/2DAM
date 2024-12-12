package com.example.apptareas.domain.usecases.events_usercases

import com.example.apptareas.data.remote.EventsRepository
import javax.inject.Inject

class GetEventUseCase @Inject  constructor(private val eventsRepository: EventsRepository) {
    suspend operator fun invoke (eventId : Int) =
        eventsRepository.getEvent(eventId)
}