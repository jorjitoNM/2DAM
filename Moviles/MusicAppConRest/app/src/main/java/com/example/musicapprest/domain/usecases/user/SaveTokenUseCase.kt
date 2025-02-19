package com.example.musicapprest.domain.usecases.user

import com.example.musicapprest.data.DataStoreRepository
import com.example.musicapprest.data.remote.security.Token
import javax.inject.Inject

class SaveTokenUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
) {
    suspend fun invoke (token : Token) {
        dataStoreRepository.saveLoginToken(token.login)
        dataStoreRepository.saveRefreshToken(token.refresh)
    }
}