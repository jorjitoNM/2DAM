package com.example.apptareas.data.remote.api_service

import com.example.apptareas.data.remote.model.notes.NoteRemote
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface NotesService {

    @GET("comments/{id}")
    suspend fun getNotes (@Path("id") userId : Int) : List<NoteRemote>

    @DELETE("comments/{id}")
    suspend fun deleteNote (@Path("id") noteId : Int)

    @DELETE("comments/{id}")
    suspend fun deleteEventNotes (@Path("id") eventId : Int)
}