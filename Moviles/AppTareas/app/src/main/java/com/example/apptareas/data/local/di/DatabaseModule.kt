package com.example.apptareas.data.local.di

import android.content.Context
import androidx.room.Room
import com.example.apptareas.data.local.AppDataBase
import com.example.apptareas.data.local.UserDao
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
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDataBase {
        return Room.databaseBuilder(
            appContext,
            AppDataBase::class.java,
            "app.db"
        )   .fallbackToDestructiveMigration()
            .build()
    }



    @Provides
    fun proviudeUserDao(appDatabase: AppDataBase): UserDao {
        return appDatabase.userDao()
    }
}