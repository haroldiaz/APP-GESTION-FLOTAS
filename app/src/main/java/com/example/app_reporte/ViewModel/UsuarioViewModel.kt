package com.example.app_reporte.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_reporte.Data.Bd
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import com.example.app_reporte.Models.Usuario

class UsuarioViewModel(private val db: Bd) : ViewModel() {

    val listaUsuarios: Flow<List<Usuario>> = db.UserDao().leerUsuarios()

    fun insertarUsuario(nombre: String, edad: Int) {
        viewModelScope.launch {
            db.UserDao().insertarUsuario(Usuario(nombre = nombre, edad = edad))
        }
    }
    suspend fun actualizarUsuario(user: Usuario) {
        db.UserDao().actualizarUsuario(user)
    }

    suspend fun eliminarUser(user:Usuario){
        db.UserDao().eliminarUsuario(user)
    }
}


