package com.example.apptareas.ui.user_profile

import com.example.apptareas.domain.model.User
import com.example.apptareas.ui.common.UiEvent

data class ProfileFragmentState(
    val user : User = User(),
    val appEvent : UiEvent? = null,
)
