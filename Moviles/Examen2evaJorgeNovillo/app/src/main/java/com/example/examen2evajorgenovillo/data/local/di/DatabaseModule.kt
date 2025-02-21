package com.example.examen2evajorgenovillo.data.local.di

import android.content.Context
import androidx.room.Room
import com.example.examen2evajorgenovillo.data.local.AppDatabase
import com.example.examen2evajorgenovillo.data.local.dao.InformesDao
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
            "app.db"
        ).createFromAsset("database/informes.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideInformesDao (appDatabase: AppDatabase): InformesDao {
        return appDatabase.informesDao()
    }
}