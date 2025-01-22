package com.example.primeraapp.ui.pantallaAdd

import com.example.primeraapp.domain.modelo.Post

sealed interface AddEvent {
    class AddPost(var post: Post) : AddEvent
    data object ErrorMostrado : AddEvent

}