package com.example.apptareas.domain.usecases

import com.example.apptareas.data.remote.EventsRepository
import com.example.apptareas.domain.model.Event
import com.example.apptareas.domain.model.toEventRemote
import javax.inject.Inject

class UpdateEventUseCase @Inject  constructor(private val eventsRepository: EventsRepository) {
    suspend operator fun invoke (event : Event) =
        eventsRepository.update(event.toEventRemote())
}