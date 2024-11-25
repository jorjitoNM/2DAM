package com.example.myapplication.data.remote.model


import com.example.myapplication.R
import com.example.myapplication.domain.model.Character
import com.google.gson.annotations.SerializedName

data class CharacterRemote(
    @SerializedName("created")
    val created: String,
    @SerializedName("episode")
    val episode: List<String>,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("location")
    val location: Location,
    @SerializedName("name")
    val name: String,
    @SerializedName("origin")
    val origin: Origin,
    @SerializedName("species")
    val species: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String
)
fun CharacterRemote.toCharacter() =
    Character(
        id = id,
        name = name,
        alive = parseStatus(status),
        species = species,
        image = image,
    )

fun parseStatus(status: String): Boolean {
    return status == R.string.alive.toString()
}
