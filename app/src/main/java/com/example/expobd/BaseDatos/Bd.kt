package com.example.app_reporte.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.app_reporte.Dao.UsuarioDao
import com.example.app_reporte.Dao.VehiculoDao
import com.example.app_reporte.Models.Vehiculo
import com.example.app_reporte.Models.Usuario

@Database(entities = [Usuario::class,Vehiculo::class]
            , version = 1)
abstract class Bd : RoomDatabase() {
    abstract fun UserDao(): UsuarioDao
    abstract fun vehiculoDao(): VehiculoDao

    companion object {
        @Volatile private var INSTANCE: Bd? = null

        fun getDatabase(context: Context): Bd {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    Bd::class.java,
                    "bd"
                ).build().also { INSTANCE = it }
            }
        }
    }
}