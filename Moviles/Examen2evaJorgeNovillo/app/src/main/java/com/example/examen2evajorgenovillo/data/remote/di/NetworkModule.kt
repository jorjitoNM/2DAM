package com.example.examen2evajorgenovillo.data.remote.di


import com.example.examen2evajorgenovillo.BuildConfig
import com.example.examen2evajorgenovillo.data.DataStoreRepository
import com.example.examen2evajorgenovillo.data.remote.api_services.AlumnosService
import com.example.examen2evajorgenovillo.data.remote.api_services.RatonesService
import com.example.examen2evajorgenovillo.data.remote.api_services.UsersService
import com.example.examen2evajorgenovillo.data.remote.security.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

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
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
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
    fun provideRatonesService (retrofit: Retrofit): RatonesService {
        return retrofit.create(RatonesService::class.java);
    }

    @Provides
    fun provideAlumnosService (retrofit: Retrofit): AlumnosService {
        return retrofit.create(AlumnosService::class.java);
    }

    @Provides
    fun provideUsersService (retrofit: Retrofit): UsersService {
        return retrofit.create(UsersService::class.java);
    }

    @Singleton
    @Provides
    fun provideAuthInterceptor(dataStoreRepository: DataStoreRepository): AuthInterceptor =
        AuthInterceptor(dataStoreRepository)

}