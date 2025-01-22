package com.example.primeraapp.ui.pantallaPostsOfUser

sealed interface PostUserEvent {
    class DeletePersona(val postId: Int) : PostUserEvent
    class GetPostsOfUser(val userId: Int) : PostUserEvent
    data object ErrorMostrado : PostUserEvent
    data object UndoDelete : PostUserEvent
}