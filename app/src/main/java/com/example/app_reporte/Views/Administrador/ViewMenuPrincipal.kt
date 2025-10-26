package com.example.app_reporte.Views.Administrador

import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun MenuPrincipal(navController: NavController? = null) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var mensajeDialog by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(60.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Menu Principal",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontSize = 18.sp,
                textAlign = TextAlign.Left
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                navController?.navigate("verUsuarios")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .padding(4.dp),
            shape = RoundedCornerShape(6.dp),
            border = BorderStroke(1.dp, Color.Black),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            )
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Icono usuario",
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text("Usuarios", fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = {
                // Navegación solo
                navController?.navigate("Vehiculos")
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
            Text("Vehiculos", fontSize = 18.sp)
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = {
                // Navegación solo
                navController?.navigate("MenuPrincipal") {
                    popUpTo("login") { inclusive = true }
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
            Text("CheckList", fontSize = 18.sp)
        }
    }


}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewMenu() {
    MenuPrincipal()
}
