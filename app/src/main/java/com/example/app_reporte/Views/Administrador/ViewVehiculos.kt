package com.example.app_reporte.Views.Administrador

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.app_reporte.Data.Bd
import com.example.app_reporte.Models.Vehiculo
import com.example.app_reporte.ViewModel.VehiculoViewModel

import kotlinx.coroutines.launch

// Botón flotante
@Composable
fun BotonFlotante(onClick: () -> Unit, modifier: Modifier = Modifier) {
    FloatingActionButton(onClick = onClick, modifier = modifier) {
        Icon(Icons.Filled.Add, contentDescription = "Agregar")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewVehiculos(navController: NavController) {
    val context = LocalContext.current
    val db = remember { Bd.getDatabase(context) }
    val viewModel = remember { VehiculoViewModel(db) }

    val lista_vehiculos by viewModel.listaVehiculos.collectAsState(initial = emptyList())
    val scope = rememberCoroutineScope()

    var selectedVehiculo by remember { mutableStateOf<Vehiculo?>(null) }
    var isEditing by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Usuarios") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        floatingActionButton = {
            BotonFlotante(
                onClick = {
                    selectedVehiculo = Vehiculo(0, "", "")
                    name = ""
                    age = ""
                    isEditing = true
                },
                modifier = Modifier.padding(16.dp)
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center
            ) {
                if (lista_vehiculos.isEmpty()) {
                    Text("📭 No hay Vehiculos registrados aún.", style = MaterialTheme.typography.bodyLarge)
                } else {
                    LazyColumn {
                        items(lista_vehiculos) { vehiculos ->
                            var showDeleteDialog by remember { mutableStateOf(false) }

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                                    .clickable {
                                       // selectedVehiculo = vehiculo
                                        isEditing = false
                                        //name = user.nombre
                                        //age = user.edad.toString()
                                    },
                                elevation = CardDefaults.cardElevation(6.dp)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column {
                                       // Text("🧑 ${user.nombre}", style = MaterialTheme.typography.titleMedium)
                                       // Text("🎂 ${user.edad} años", style = MaterialTheme.typography.bodyMedium)
                                    }
                                    Row {
                                        IconButton(onClick = {
                                           // selectedVehiculo = user
                                            isEditing = true
//name = user.nombre
                                           /// age = user.edad.toString()
                                        }) {
                                            Icon(Icons.Default.Edit, contentDescription = "Editar")
                                        }

                                        IconButton(onClick = {
                                           // selectedVehiculo = user
                                            showDeleteDialog = true
                                        }) {
                                            Icon(Icons.Default.Delete, contentDescription = "Eliminar")
                                        }

                                        if (showDeleteDialog && selectedVehiculo != null) {
                                            AlertDialog(
                                                onDismissRequest = { showDeleteDialog = false },
                                                title = { Text("Confirmar eliminación") },
                                             //   text = { Text("¿Seguro que deseas eliminar a \"${selectedVehiculo!!.nombre}\"?") },
                                                confirmButton = {
                                                    TextButton(onClick = {
                                                        scope.launch {
                                                            //viewModel.eliminarUser(selectedVehiculo!!)
                                                            showDeleteDialog = false
                                                            selectedVehiculo = null
                                                        }
                                                    }) {
                                                        Text("Eliminar", color = MaterialTheme.colorScheme.error)
                                                    }
                                                },
                                                dismissButton = {
                                                    TextButton(onClick = { showDeleteDialog = false }) {
                                                        Text("Cancelar")
                                                    }
                                                }
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
/*
            // ✅ Modal unificado (Agregar, Editar o Ver)
            if (selectedUser != null) {
                EditarUsuarioModal(
                    selectedUser = selectedUser,
                    isEditing = isEditing,
                    name = name,
                    age = age,
                    onNameChange = { name = it },
                    onAgeChange = { age = it },
                    onSaveUser = { vehiculo ->
                        scope.launch {
                            if (usuario.id == 0) {
                                viewModel.insertarUsuario(usuario.nombre, usuario.edad)
                            } else {
                                viewModel.actualizarUsuario(usuario)
                            }
                        }
                    },
                    onClose = {
                        selectedUser = null
                        isEditing = false
                    }
                )
            }
        */
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewVerVehiculo() {
    val navController = rememberNavController()
    ViewUsuarios(navController)
}
