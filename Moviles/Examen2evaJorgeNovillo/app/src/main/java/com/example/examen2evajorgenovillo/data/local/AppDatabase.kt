package com.example.examen2evajorgenovillo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.examen2evajorgenovillo.data.local.dao.InformesDao
import com.example.examen2evajorgenovillo.domain.model.Informe

@Database(entities = [Informe::class],
    version = 2,
    exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun informesDao() : InformesDao
}