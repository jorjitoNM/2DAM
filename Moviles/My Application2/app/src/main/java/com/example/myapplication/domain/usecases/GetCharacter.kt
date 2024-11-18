package com.example.myapplication.domain.usecases;

import com.example.myapplication.data.SongsRepository
import javax.inject.Inject

class GetCharacter @Inject constructor(private val characterRepository: SongsRepository) {
    suspend operator fun invoke(id : Int) = characterRepository.fetchCharacter(id)
}