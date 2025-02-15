package com.example.musicappcompse.domain.usecases.user

import com.example.musicappcompse.data.DataStoreRepository
import javax.inject.Inject

class SaveUserNameUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
) {
    suspend fun invoke (name : String) = dataStoreRepository.saveUserName(name)
}