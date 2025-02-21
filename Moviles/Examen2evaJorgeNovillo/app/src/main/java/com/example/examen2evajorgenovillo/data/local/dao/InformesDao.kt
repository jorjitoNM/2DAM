package com.example.examen2evajorgenovillo.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.examen2evajorgenovillo.domain.model.Informe

@Dao
interface InformesDao {
    @Query("SELECT * FROM informe")
    fun getAll () : List<Informe>
}