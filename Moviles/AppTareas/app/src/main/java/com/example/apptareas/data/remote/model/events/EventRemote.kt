package com.example.apptareas.data.remote.model.events


import com.example.apptareas.domain.model.Event
import com.google.gson.annotations.SerializedName

data class EventRemote(
    @SerializedName("body")
    val body: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
)
fun Event.toEvent() = Event(id,title,body,userId)