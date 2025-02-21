package com.example.examen2evajorgenovillo.domain.usecases.user

import com.example.examen2evajorgenovillo.data.DataStoreRepository
import com.example.examen2evajorgenovillo.data.remote.security.Token
import javax.inject.Inject

class SaveTokenUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {
    suspend fun invoke (token : Token) = dataStoreRepository.saveToken(token.accessToken)
}