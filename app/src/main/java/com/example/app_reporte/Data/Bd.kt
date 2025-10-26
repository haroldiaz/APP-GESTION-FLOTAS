package com.example.app_reporte.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.app_reporte.Models.Usuario
import com.example.app_reporte.Dao.UsuarioDao
import com.example.app_reporte.Dao.VehiculoDao
import com.example.app_reporte.Models.Vehiculo


@Database(entities = [Usuario::class,Vehiculo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun UserDao(): UsuarioDao
    abstract fun vehiculoDao(): VehiculoDao
    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "mi_bd"
                ).build().also { INSTANCE = it }
            }
        }
    }
}

