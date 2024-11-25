package com.example.apptareas.data.remote.di

import com.example.apptareas.BuildConfig
import com.example.apptareas.data.remote.api_service.EventsService
import com.example.apptareas.data.remote.api_service.NotesService
import com.example.apptareas.data.remote.api_service.TodosService
import com.example.apptareas.data.remote.api_service.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


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
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
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
    fun provideEventService (retrofit: Retrofit): EventsService {
        return retrofit.create(EventsService::class.java);
    }

    @Provides
    fun provideNotesService (retrofit: Retrofit): NotesService {
        return retrofit.create(NotesService::class.java);
    }

    @Provides
    fun provideTodosService (retrofit: Retrofit): TodosService {
        return retrofit.create(TodosService::class.java);
    }

    @Provides
    fun provideUserService (retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java);
    }
}