package com.example.app_reporte.Views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.app_reporte.Models.Usuario

import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarUsuarioModal(
    selectedUser: Usuario?,
    isEditing: Boolean,
    name: String,
    age: String,
    onNameChange: (String) -> Unit,
    onAgeChange: (String) -> Unit,
    onSaveUser: (Usuario) -> Unit,
    onClose: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = { onClose() },
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            if (isEditing) {
                Text(
                    text = if (selectedUser?.id == 0) "➕ Agregar usuario" else "✏️ Editar usuario",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = name,
                    onValueChange = onNameChange,
                    label = { Text("Nombre") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = age,
                    onValueChange = onAgeChange,
                    label = { Text("Edad") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        val usuario = Usuario(
                            id = selectedUser?.id ?: 0,
                            nombre = name,
                            edad = age.toIntOrNull() ?: 0
                        )
                        onSaveUser(usuario)
                        scope.launch { sheetState.hide() }
                        onClose()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(if (selectedUser?.id == 0) "Guardar" else "Actualizar")
                }
            } else {
                Text("👤 Detalles del usuario", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(10.dp))
                Text("Nombre: ${selectedUser?.nombre}", style = MaterialTheme.typography.bodyLarge)
                Text("Edad: ${selectedUser?.edad}", style = MaterialTheme.typography.bodyLarge)

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        scope.launch { sheetState.hide() }
                        onClose()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Cerrar")
                }
            }
        }
    }
}
