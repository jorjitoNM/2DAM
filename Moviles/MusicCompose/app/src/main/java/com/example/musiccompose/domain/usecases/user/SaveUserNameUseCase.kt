package com.example.musiccompose.domain.usecases.user

import com.example.musiccompose.data.DataStoreRepository
import javax.inject.Inject

class SaveUserNameUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
) {
    suspend operator fun invoke (name : String) = dataStoreRepository.saveUserName(name)
}