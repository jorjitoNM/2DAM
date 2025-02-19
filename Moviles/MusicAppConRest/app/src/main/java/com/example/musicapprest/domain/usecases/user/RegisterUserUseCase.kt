package com.example.musicapprest.domain.usecases.user

import com.example.musicapprest.R
import com.example.musicapprest.common.NetworkResult
import com.example.musicapprest.common.StringProvider
import com.example.musicapprest.data.UsersRepository
import com.example.musicapprest.domain.model.User
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val usersRepository: UsersRepository,
    private val stringProvider: StringProvider,
) {
    suspend fun invoke(user : User) : NetworkResult<String> {
        if (!user.email.contains("@"))
            return NetworkResult.Error(stringProvider.getString(R.string.invalid_email_format))
        else
            return usersRepository.registerUser(user)
    }
}