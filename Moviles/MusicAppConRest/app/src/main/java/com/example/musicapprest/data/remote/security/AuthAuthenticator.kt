package com.example.musicapprest.data.remote.security

import com.example.musicapprest.data.remote.api_services.UsersService
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject
import dagger.Lazy

class AuthAuthenticator @Inject constructor(
    private val tokenProvider: TokenProvider,
    private val service : Lazy<UsersService>,
): Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val token = runBlocking {
            tokenProvider.getToken().first()
        }
        return runBlocking {
            val newToken = getNewToken(token)

            if (!newToken.isSuccessful || newToken.body() == null) { //Couldn't refresh the token, so restart the login process
                tokenProvider.deleteToken()
            }

            newToken.body()?.let {
                tokenProvider.saveToken(it.login)
                response.request.newBuilder()
                    .header("Authorization", "Bearer ${it.login}")
                    .build()
            }
        }
    }

    private suspend fun getNewToken(refreshToken: String?): retrofit2.Response<Token> {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return service.get().refreshToken("Bearer $refreshToken")
    }
}
