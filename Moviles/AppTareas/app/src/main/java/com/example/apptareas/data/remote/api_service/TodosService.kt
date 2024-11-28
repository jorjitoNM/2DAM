package com.example.apptareas.data.remote.api_service

import com.example.apptareas.data.remote.model.todos.TodoRemote
import retrofit2.Response
import retrofit2.http.GET

interface TodosService {

    @GET("todos")
    suspend fun getTodos () : Response<List<TodoRemote>>

}