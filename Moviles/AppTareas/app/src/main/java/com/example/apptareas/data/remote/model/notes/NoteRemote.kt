package com.example.apptareas.data.remote.model.notes


import com.google.gson.annotations.SerializedName

data class NoteRemote(
    @SerializedName("body")
    val body: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("postId")
    val postId: Int
)