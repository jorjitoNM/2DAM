package com.example.apptareas.domain.usecases

import com.example.apptareas.data.remote.EventsRepository
import com.example.apptareas.domain.model.Event
import com.example.viewmodel.data.remote.NetworkResult
import javax.inject.Inject

class GetEventsUseCase @Inject constructor(private val eventsRepository: EventsRepository) {
    suspend operator fun invoke (userId : Int) :NetworkResult<List<Event>> =
        eventsRepository.getEvents(userId)
}