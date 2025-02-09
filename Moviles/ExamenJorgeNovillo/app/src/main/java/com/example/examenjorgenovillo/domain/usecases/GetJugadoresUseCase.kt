package com.example.examenjorgenovillo.domain.usecases

import com.example.examenjorgenovillo.data.remote.JugadoresRepository
import javax.inject.Inject

class GetJugadoresUseCase @Inject constructor(private val jugadoresRepository: JugadoresRepository){
    suspend operator fun invoke (id : Int) =
        jugadoresRepository.getJugadores(id)
}