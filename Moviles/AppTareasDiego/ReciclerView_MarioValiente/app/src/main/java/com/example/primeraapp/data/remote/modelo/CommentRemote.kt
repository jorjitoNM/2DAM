package com.example.primeraapp.data.remote.modelo

import com.example.primeraapp.domain.modelo.Comment

data class CommentRemote(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String,
)

fun CommentRemote.toComment(): Comment =
    Comment(
        postId = postId,
        id = id,
        name = name,
        email = email,
        body = body,
    )
