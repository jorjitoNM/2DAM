package com.example.apptareascompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.apptareascompose.data.local.modelo.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}