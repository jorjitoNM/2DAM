package com.example.myapplication.ui.mainScreen

import com.example.myapplication.domain.model.Character

data class MainState(
    val characters: List<Character> = emptyList(),
)