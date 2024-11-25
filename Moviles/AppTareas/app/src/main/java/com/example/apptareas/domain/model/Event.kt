package com.example.apptareas.domain.model

import com.example.apptareas.R
import com.example.apptareas.data.remote.model.events.EventRemote

data class Event (
    val id: Int = -1,
    val title: String = R.string.event_title.toString(),
    val body: String = R.string.event_description.toString(),
    val userId: Int = -1,
    val image : String = R.string.image_provider.toString(),
)
fun Event.toEventRemote() = EventRemote(id,title,body,userId)