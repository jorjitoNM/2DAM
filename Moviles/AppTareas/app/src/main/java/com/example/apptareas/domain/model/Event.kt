package com.example.apptareas.domain.model


import com.example.apptareas.data.remote.model.events.EventRemote
import com.example.apptareas.utilities.Constantes
import com.example.primerxmlmvvm.R

data class Event (
    val id: Int = -1,
    val title: String = R.string.event_title.toString(),
    val body: String = R.string.event_description.toString(),
    val userId: Int = -1,
    val image : String = Constantes.IMAGE_PROVIDER,
)
fun Event.toEventRemote() = EventRemote(id,title,body,userId)