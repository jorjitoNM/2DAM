package com.example.primeraapp.ui.pantallaComments

sealed interface CommentEvent {
    class GetComments(val id: Int) : CommentEvent
    data object ErrorMostrado : CommentEvent
    data object UndoDelete : CommentEvent
}