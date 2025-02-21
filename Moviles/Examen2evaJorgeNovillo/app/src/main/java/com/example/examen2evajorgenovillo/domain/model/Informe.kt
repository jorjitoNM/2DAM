package com.example.examen2evajorgenovillo.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Informe(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val contenido : String,
    val nivel : Int,
)
