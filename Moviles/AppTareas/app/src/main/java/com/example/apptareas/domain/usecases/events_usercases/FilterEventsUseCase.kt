package com.example.apptareas.domain.usecases.events_usercases

import com.example.apptareas.domain.model.Event
import javax.inject.Inject


class FilterEventsUseCase @Inject constructor() {
    operator fun invoke (eventName:  String, events: List<Event>) : List<Event> =
        events.filter { e -> e.title.contains(eventName) }
}