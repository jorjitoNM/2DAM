package com.example.apptareas.domain.model

import com.example.apptareas.data.remote.NetworkResult
import com.example.apptareas.utilities.Constantes

data class User(
    val id : Int = -1,
    val name : String = "Monica",
    val username : String = "Galindo",
    val phone : String = "12345678"
)

fun User.validateUser(user: User): NetworkResult<User> =
    if (user.username.contains("a"))
        NetworkResult.Success(user)
    else
        NetworkResult.Error(Constantes.INVALID_USERNAME)
