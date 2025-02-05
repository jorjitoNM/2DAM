package com.example.hospitalroomcompose.data.local.di

import android.content.Context
import androidx.room.Room
import com.example.hospitalroomcompose.data.common.Constantes
import com.example.hospitalroomcompose.data.local.AppDatabase
import com.example.hospitalroomcompose.data.local.dao.MedicalRecordsDao
import com.example.hospitalroomcompose.data.local.dao.MedicationsDao
import com.example.hospitalroomcompose.data.local.dao.PatientsDao
import com.example.hospitalroomcompose.data.local.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            Constantes.APP_DB
        ).createFromAsset("database/hospital.db")
            .fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    fun provideUsersDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Provides
    fun providePatientsDao(appDatabase: AppDatabase): PatientsDao {
        return appDatabase.patientsDao()
    }

    @Provides
    fun provideMedicalRecordsDao(appDatabase: AppDatabase): MedicalRecordsDao {
        return appDatabase.medicalRecordsDao()
    }

    @Provides
    fun provideMedicationsDao(appDatabase: AppDatabase): MedicationsDao {
        return appDatabase.medicationsDao()
    }
}