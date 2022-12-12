package com.example.kursova.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LightEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val name:String,
    @ColumnInfo(name = "value") val value:Float,
    @ColumnInfo(name = "sensor") val sensor:String
    )

@Entity
data class AxisEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val name:String,
    @ColumnInfo(name = "xValue") val xValue: Float,
    @ColumnInfo(name = "yValue") val yValue: Float,
    @ColumnInfo(name = "sensor") val sensor:String
    )