package com.example.musicapprest.data.remote.di


import com.example.musicapprest.BuildConfig
import com.example.musicapprest.data.remote.api_services.PlaylistsService
import com.example.musicapprest.data.remote.api_services.SongsService
import com.example.musicapprest.data.remote.api_services.UsersService
import com.example.musicapprest.data.remote.security.AuthAuthenticator
import com.example.musicapprest.data.remote.security.AuthInterceptor
import com.example.musicapprest.data.remote.security.TokenProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import dagger.Lazy

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideHTTPLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }

    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor,
        authenticator: AuthAuthenticator,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .authenticator(authenticator)
            .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideSongsService (retrofit: Retrofit): SongsService {
        return retrofit.create(SongsService::class.java);
    }

    @Provides
    fun providePlaylistsService (retrofit: Retrofit): PlaylistsService {
        return retrofit.create(PlaylistsService::class.java);
    }

    @Provides
    fun provideUsersService (retrofit: Retrofit): UsersService {
        return retrofit.create(UsersService::class.java);
    }

    @Singleton
    @Provides
    fun provideAuthInterceptor(tokenProvider: TokenProvider): AuthInterceptor =
        AuthInterceptor(tokenProvider)

    @Singleton
    @Provides
    fun provideAuthAuthenticator(tokenProvider: TokenProvider,usersService: Lazy<UsersService>): AuthAuthenticator =
        AuthAuthenticator(tokenProvider,usersService)
}