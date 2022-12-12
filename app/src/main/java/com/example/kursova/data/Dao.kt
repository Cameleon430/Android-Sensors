package com.example.kursova.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface LightDao{

    @Query("SELECT * FROM LightEntity")
    suspend fun getAll(): List<LightEntity>

    @Query("SELECT * FROM LightEntity WHERE id == :id")
    suspend fun get(id: Int): LightEntity?

    @Insert(onConflict = REPLACE)
    suspend fun insert(light: LightEntity)

    @Query("DELETE FROM LightEntity WHERE id == :id")
    suspend fun delete(id: Int)
}

@Dao
interface AxisDao{

    @Query("SELECT * FROM AxisEntity")
    suspend fun getAll(): List<AxisEntity>

    @Query("SELECT * FROM AxisEntity WHERE id == :id")
    suspend fun get(id: Int): AxisEntity?

    @Insert(onConflict = REPLACE)
    suspend fun insert(axis: AxisEntity)

    @Query("DELETE FROM AxisEntity WHERE id == :id")
    suspend fun delete(id: Int)
}