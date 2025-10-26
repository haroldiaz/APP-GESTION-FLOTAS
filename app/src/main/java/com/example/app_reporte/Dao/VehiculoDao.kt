package com.example.app_reporte.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.app_reporte.Models.Vehiculo
import kotlinx.coroutines.flow.Flow

@Dao
interface VehiculoDao{

    @Insert
    suspend fun insertarVehiculo(vehiculo: Vehiculo)

    @Query("SELECT * FROM vehiculo" )
    fun leerVehiculo(): Flow<List<Vehiculo>>

    @Update
    suspend fun actualizarVehiculo(vehiculo: Vehiculo)

    @Delete
    suspend  fun eliminarVehiculo(vehiculo:Vehiculo)
}

