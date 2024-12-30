package com.example.apptareas.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.apptareas.data.local.model.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getAll(): List<UserEntity>

    @Query("SELECT * FROM users WHERE id = :id")
    fun getCoche(id: Int): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(toUserEntity: UserEntity)
}