package com.example.app_reporte.ViewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_reporte.Data.Bd
import kotlinx.coroutines.flow.Flow
import com.example.app_reporte.Models.Vehiculo
import kotlinx.coroutines.launch

class VehiculoViewModel (private val db: Bd): ViewModel()
{
  val listaVehiculos:Flow<List<Vehiculo>> =db.vehiculoDao().leerVehiculo()

    fun insertarVehiculo(placa: String, marca: String )  {
        viewModelScope.launch {
            db.vehiculoDao().insertarVehiculo(Vehiculo(placa = placa, marca = marca ))
        }
    }

    suspend fun actualizarVehiculo(vehiculo: Vehiculo){
        db.vehiculoDao().actualizarVehiculo(vehiculo)
    }
    suspend fun eliminarVehiculo(vehiculo: Vehiculo){
        db.vehiculoDao().eliminarVehiculo(vehiculo)
    }
}