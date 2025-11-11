package com.example.app_reporte


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController



import com.example.app_reporte.Views.Conductor.ViewMenuConductor
import com.example.app_reporte.Views.Inicio.SplashScreen
import com.example.app_reporte_copia.Views.LoginScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App()
        }
    }
}

@Composable
fun App(){
    val navController = rememberNavController()

    Surface(color = MaterialTheme.colorScheme.background) {
        NavHost(
            navController = navController,
            startDestination = "Splash" // 👈 pantalla inicial
        ) {
            // 📌 Pantalla 0: SplashScren
            composable("Splash") {
                SplashScreen(navController = navController)
            }
            // 📌 Pantalla 1: Login
            composable("login") {
                LoginScreen(navController = navController)
            }
            //Pantalla Principal Admin
            // 📌 Pantalla 2: MenuPrincipal Administrador
            composable("MenuPrincipal") {
              //  MenuPrincipal(navController = navController)
            }
            // 📌 Pantalla 2: Ver Usuario
            composable("verUsuarios") {
               // ViewUsuarios(navController = navController)
            }
            // 📌 Pantalla 3: Ver Vehiculos
            composable("Vehiculos"){
                //ViewVehiculos(navController = navController)
            }

            //-----------------------------------
            //Pantalla Principal Conductor
            composable("MenuConductor"){
                ViewMenuConductor(navController = navController)
            }
            //informacion
            //checklist

        }
    }
}

