package com.example.primeraapp.data.local.di

import android.content.Context
import androidx.room.Room
import com.example.apptareascompose.data.common.Constantes
import com.example.apptareascompose.data.local.UserDao
import com.example.primeraapp.data.local.AppDatabase
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
        )
            .fallbackToDestructiveMigration().build()
    }


    @Provides
    fun provideUsersDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }
}