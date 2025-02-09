package com.example.apptareas.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.apptareas.data.local.model.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = true)
abstract class AppDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao
}