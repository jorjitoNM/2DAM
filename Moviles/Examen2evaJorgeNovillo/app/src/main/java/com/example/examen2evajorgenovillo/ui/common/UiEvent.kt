package com.example.examen2evajorgenovillo.ui.common

sealed class UiEvent {
    data object PopBackStack : UiEvent()
    data class ShowSnackbar(
        val message: String,
        val action: String? = null
    ) : UiEvent()
    data object NavigateToLogin : UiEvent()
}
