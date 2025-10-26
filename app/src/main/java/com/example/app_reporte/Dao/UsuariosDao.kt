package com.example.app_reporte.Dao


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.app_reporte.Models.Usuario
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDao {

    @Insert
    suspend fun insertarUsuario(usuario: Usuario)

    @Query("SELECT * FROM usuarios" )
    fun leerUsuarios(): Flow<List<Usuario>>

    @Update
    suspend fun actualizarUsuario(usuario: Usuario)

    @Delete
    suspend  fun eliminarUsuario(usuario:Usuario)
}

