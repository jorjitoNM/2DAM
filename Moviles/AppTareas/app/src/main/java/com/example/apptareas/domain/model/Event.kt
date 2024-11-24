package com.example.apptareas.domain.model

import com.example.apptareas.R

data class Event (
    val id: Int,
    val body: String,
    val title: String,
    val userId: Int,
    val image : String = R.string.image_provider.toString(),
)