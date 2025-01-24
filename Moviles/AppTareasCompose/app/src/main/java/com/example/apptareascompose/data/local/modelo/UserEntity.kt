package com.example.apptareascompose.data.local.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.apptareascompose.domain.model.User


@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val username: String,
    val password: String,
)

fun UserEntity.toUser() : User = User(id,username,password)