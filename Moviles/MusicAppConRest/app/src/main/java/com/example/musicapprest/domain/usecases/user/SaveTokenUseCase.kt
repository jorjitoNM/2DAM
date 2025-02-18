package com.example.musicapprest.domain.usecases.user

import com.example.musicapprest.data.remote.security.Token
import com.example.musicapprest.data.remote.security.TokenProvider
import javax.inject.Inject

class SaveUserNameUseCase @Inject constructor(
    private val tokenProvider: TokenProvider,
) {
    suspend fun invoke (token : Token) {
        tokenProvider.saveToken(token.login)
        //tokenProvider.saveRefresh(token.refresh)
    }
}