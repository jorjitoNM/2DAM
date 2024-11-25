package com.example.myapplication.data.remote.model


import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val characterRemotes: List<CharacterRemote>
)