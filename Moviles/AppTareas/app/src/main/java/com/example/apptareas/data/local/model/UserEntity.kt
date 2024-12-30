package com.example.apptareas.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.apptareas.domain.model.User

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val id : Int,
    val name : String,
    val surename : String,
)

fun UserEntity.toUser() : User =
    User(
        id = id,
        name = name,
        username = surename,
        phone = "1234"
    )
