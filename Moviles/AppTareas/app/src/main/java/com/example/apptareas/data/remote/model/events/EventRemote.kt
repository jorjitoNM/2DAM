package com.example.apptareas.data.remote.model.events


import com.example.apptareas.domain.model.Event
import com.google.gson.annotations.SerializedName

data class EventRemote(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String,
    @SerializedName("userId")
    val userId: Int
)

fun EventRemote.toEvent() = Event(id,title,body,userId)