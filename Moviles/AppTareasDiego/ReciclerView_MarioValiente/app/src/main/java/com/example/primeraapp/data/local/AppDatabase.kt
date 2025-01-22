package com.example.primeraapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.primeraapp.data.local.modelo.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}