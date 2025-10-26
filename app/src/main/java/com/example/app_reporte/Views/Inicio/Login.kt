package com.example.app_reporte.Views.Inicio


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.app_reporte.R

// import com.example.app.data.UsuariosDBHelper

@Composable
fun LoginScreen(navController: NavController? = null) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    var showDialog by remember { mutableStateOf(false) }
    var mensajeDialog by remember { mutableStateOf("") }

    //admin
    val  valUser = "admin"
    val valPass = "1234"
    //conductor
    val con = "con"
    val pass = "12"
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(60.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "NeoFlota",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontSize = 28.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            ),
            color = Color.Blue,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(80.dp))

        Text(
            text = "Iniciar Sesión",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontSize = 18.sp,
                textAlign = TextAlign.Left
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo electrónico") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if (email == valUser && password == valPass)
                {
                    // Navegación solo
                    navController?.navigate("MenuPrincipal") {
                        popUpTo("login") { inclusive = true }
                    }
                }
                else if (email == con && password == pass)
                {
                    // Navegación solo
                    navController?.navigate("MenuConductor") {
                        popUpTo("MenuPrincipal") { inclusive = true }
                    }
                }
                else{

                    // Si no coincide, muestra error
                    mensajeDialog = "Usuario o contraseña incorrectos"
                    showDialog = true
                }


            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(4.dp),
            shape = RoundedCornerShape(6.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF233D5B),
                contentColor = Color.White
            )
        ) {
            Text("Entrar", fontSize = 18.sp)
        }
    }

    // 🔹 POPUP de confirmación (solo si activas BD)
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("OK")
                }
            },
            title = { Text("Registro") },
            text = { Text(mensajeDialog) }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLogin() {
    LoginScreen()
}
