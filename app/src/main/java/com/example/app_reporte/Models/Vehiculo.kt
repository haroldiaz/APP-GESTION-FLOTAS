package com.example.app_reporte.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vehiculo")
data class Vehiculo (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    public val placa: String,
    public val marca: String
)