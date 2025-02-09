package com.example.primeraapp.data.remote.modelo

import com.example.primeraapp.domain.modelo.Post

data class PostRemote(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
)

fun PostRemote.toPost(): Post =
    Post(
        userId = userId,
        id = id,
        title = title,
        body = body,
    )