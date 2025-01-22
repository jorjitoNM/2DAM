package com.example.primeraapp.ui.pantallaPosts

sealed interface PostEvent {
    data class GetPost(val id: Int) : PostEvent
    data object ErrorMostrado : PostEvent
    data object UndoDelete : PostEvent
}