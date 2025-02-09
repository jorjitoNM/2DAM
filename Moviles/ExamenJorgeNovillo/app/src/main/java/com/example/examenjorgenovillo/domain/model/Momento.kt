package com.example.examenjorgenovillo.domain.model

data class Momento (
    val id : Int,
    val equipoCasa : String,
    val equipoFuera : String,
    val cuarto : Int,
    val tiempo : String,
    val marcadorEquipoCasa : Int,
    val marcadorEquipoFuera : Int,
)
