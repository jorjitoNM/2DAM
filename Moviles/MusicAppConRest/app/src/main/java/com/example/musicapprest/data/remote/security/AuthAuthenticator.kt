package com.example.musicapprest.data.remote.security

import com.example.musicapprest.data.remote.api_services.UsersService
import dagger.Lazy
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject

class AuthAuthenticator @Inject constructor(
    private val tokenProvider: TokenProvider,
    private val service : Lazy<UsersService>,
): Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val refreshToken = runBlocking {
            tokenProvider.getRefreshToken().first()
        }
        return runBlocking {
            val newToken = getNewToken(refreshToken)

            if (!newToken.isSuccessful || newToken.body() == null) {
                //service.get().login()
            }

            newToken.body()?.let {
                tokenProvider.saveLoginToken(it.login)
                tokenProvider.saveRefreshToken(it.refresh)
                response.request.newBuilder()
                    .header("Authorization", "Bearer ${it.refresh}")
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
