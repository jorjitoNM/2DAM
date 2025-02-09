package com.example.primeraapp.data.remote.modelo

import com.example.primeraapp.domain.modelo.User

data class UserRemote(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val phone: String,
    val website: String,
)

fun UserRemote.toUser(): User =
    User(
        id = id,
        name = name,
        username = username,
        email = email,
        phone = phone,
        website = website,
    )
