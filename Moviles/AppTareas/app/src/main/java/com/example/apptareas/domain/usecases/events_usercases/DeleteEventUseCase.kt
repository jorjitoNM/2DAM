package com.example.apptareas.domain.usecases.events_usercases

import com.example.apptareas.data.remote.EventsRepository
import com.example.apptareas.domain.model.Event
import com.example.apptareas.domain.model.toEventRemote
import javax.inject.Inject
import kotlin.random.Random

class DeleteEventUseCase @Inject constructor(private val eventsRepository: EventsRepository) {
    suspend operator fun invoke (event : Event) : Boolean {
        eventsRepository.deleteEvent(event.toEventRemote())
        return Random.nextBoolean();
    }
}