package com.example.apptareascompose.data.remote.di

import com.example.apptareascompose.data.remote.api_services.DoctorsService
import com.example.apptareascompose.data.remote.api_services.MedicalRecordService
import com.example.apptareascompose.data.remote.api_services.PatientService
import com.example.compose.BuildConfig
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
    fun providePatienService(retrofit: Retrofit): PatientService {
        return retrofit.create(PatientService::class.java);
    }

    @Provides
    fun provideMedicalRecordService(retrofit: Retrofit): MedicalRecordService {
        return retrofit.create(MedicalRecordService::class.java)
    }

    @Provides
    fun provideDoctorService (retrofit: Retrofit): DoctorsService {
        return retrofit.create(DoctorsService::class.java)
    }
}