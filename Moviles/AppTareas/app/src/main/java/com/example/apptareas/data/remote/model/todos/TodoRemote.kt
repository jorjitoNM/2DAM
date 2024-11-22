package com.example.apptareas.data.remote.model.todos


import com.google.gson.annotations.SerializedName

data class TodoRemote(
    @SerializedName("completed")
    val completed: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
)