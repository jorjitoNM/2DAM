package com.example.playlistcompose.domain.usecases.user

import com.example.playlistcompose.data.DataStoreRepository
import javax.inject.Inject

class SaveUserNameUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
) {
    suspend operator fun invoke (name : String) = dataStoreRepository.saveUserName(name)
}