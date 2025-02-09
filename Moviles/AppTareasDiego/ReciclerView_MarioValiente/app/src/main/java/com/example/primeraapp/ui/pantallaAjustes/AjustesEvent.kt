package com.example.primeraapp.ui.pantallaAjustes

import com.example.primeraapp.domain.modelo.User

sealed interface AjustesEvent {
    class GetUser(val id: Int) : AjustesEvent
    class UpdateUser(val user: User) : AjustesEvent
    data object ErrorMostrado : AjustesEvent
}