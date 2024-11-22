package com.example.apptareas.data.remote.model.user


import com.example.apptareas.domain.model.User
import com.google.gson.annotations.SerializedName

data class UserRemote(
    @SerializedName("address")
    val address: Address,
    @SerializedName("company")
    val company: Company,
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("website")
    val website: String
)
fun UserRemote.toUser() = User(
    id = id,
    name = name,
    username = username,
    phone = phone,
)