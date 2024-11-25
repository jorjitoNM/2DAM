package com.example.apptareas.data.remote.api_service

import com.example.apptareas.data.remote.model.events.EventRemote
import com.example.apptareas.domain.model.Event
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EventsService {

    @GET("posts/{id}")
    suspend fun getEvents (@Path("id") userId : Int) : Response<List<EventRemote>>

    @DELETE("posts/{id}")
    suspend fun deleteEvent(@Path("id") eventId : Int)

    @POST("post/{id}")
    suspend fun updateEvent(@Path("id") eventId : Int, @Body() event : EventRemote) : Response<EventRemote>
}