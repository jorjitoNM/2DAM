package com.example.apptareas.data.remote.api_service

import com.example.apptareas.data.remote.model.events.EventRemote
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface EventsService {

    @GET("posts")
    suspend fun getEvents () : List<EventRemote>

    @DELETE("posts")
    suspend fun deleteEvent(@Path("id") eventId : Int)
}