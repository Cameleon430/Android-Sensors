package com.example.kursova.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [LightEntity::class, AxisEntity::class],
    version = 1
)

abstract class SensorDatabase:RoomDatabase(){
    abstract fun provideLightDao():LightDao

    abstract fun provideAxisDao():AxisDao
}