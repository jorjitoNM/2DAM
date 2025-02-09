package com.example.hospitalroomcompose.domain.usecases.user

import com.example.hospitalroomcompose.data.DataStoreRepository
import javax.inject.Inject

class SaveUserNameUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
) {
    suspend operator fun invoke (name : String) = dataStoreRepository.saveUserName(name)
}