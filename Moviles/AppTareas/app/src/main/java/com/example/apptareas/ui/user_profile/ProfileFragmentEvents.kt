package com.example.apptareas.ui.user_profile

interface ProfileFragmentEvents {
    data class GetUser (val userId : Int) : ProfileFragmentEvents
    data object EventDone : ProfileFragmentEvents
}