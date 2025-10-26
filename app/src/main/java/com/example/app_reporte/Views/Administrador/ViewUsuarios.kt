package com.example.app_reporte.Views.Administrador

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.app_reporte.Data.Bd
import com.example.app_reporte.Models.Usuario
import com.example.app_reporte.Views.EditarUsuarioModal
import com.example.app_reporte.ViewModel.UsuarioViewModel

import kotlinx.coroutines.launch

// Botón flotante
@Composable
fun bt(onClick: () -> Unit, modifier: Modifier = Modifier) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier)
    {
        Icon(Icons.Filled.Add, contentDescription = "Agregar")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewUsuarios(navController: NavController) {

    if(LocalInspectionMode.current.not())
    {



    }
    val context = LocalContext.current
    val db = remember { Bd.getDatabase(context) }
    val viewModel = remember { UsuarioViewModel(db) }
    val users by viewModel.listaUsuarios.collectAsState(initial = emptyList())
    val scope = rememberCoroutineScope()
    var selectedUser by remember { mutableStateOf<Usuario?>(null) }
    var isEditing by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Vehiculos") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        floatingActionButton = {
            bt(
                onClick = {
                    selectedUser = Usuario(0, "", 0)
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
                    .padding(20.dp)
            ) {
                if (users.isEmpty()) {
                    Text("📭 No hay usuarios registrados aún.", style = MaterialTheme.typography.bodyLarge)
                } else {
                    LazyColumn {
                        items(users) { user ->
                            var showDeleteDialog by remember { mutableStateOf(false) }

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                                    .clickable {
                                        selectedUser = user
                                        isEditing = false
                                        name = user.nombre
                                        age = user.edad.toString()
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
                                        Text("🧑 ${user.nombre}", style = MaterialTheme.typography.titleMedium)
                                        Text("🎂 ${user.edad} años", style = MaterialTheme.typography.bodyMedium)
                                    }
                                    Row {
                                        IconButton(onClick = {
                                            selectedUser = user
                                            isEditing = true
                                            name = user.nombre
                                            age = user.edad.toString()
                                        }) {
                                            Icon(Icons.Default.Edit, contentDescription = "Editar")
                                        }

                                        IconButton(onClick = {
                                            selectedUser = user
                                            showDeleteDialog = true
                                        }) {
                                            Icon(Icons.Default.Delete, contentDescription = "Eliminar")
                                        }

                                        if (showDeleteDialog && selectedUser != null) {
                                            AlertDialog(
                                                onDismissRequest = { showDeleteDialog = false },
                                                title = { Text("Confirmar eliminación") },
                                                text = { Text("¿Seguro que deseas eliminar a \"${selectedUser!!.nombre}\"?") },
                                                confirmButton = {
                                                    TextButton(onClick = {
                                                        scope.launch {
                                                            viewModel.eliminarUser(selectedUser!!)
                                                            showDeleteDialog = false
                                                            selectedUser = null
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

            // ✅ Modal unificado (Agregar, Editar o Ver)
            if (selectedUser != null) {
                EditarUsuarioModal(
                    selectedUser = selectedUser,
                    isEditing = isEditing,
                    name = name,
                    age = age,
                    onNameChange = { name = it },
                    onAgeChange = { age = it },
                    onSaveUser = { usuario ->
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
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewVerUsuarios() {
    val navController = rememberNavController()
    ViewUsuarios(navController)
}
