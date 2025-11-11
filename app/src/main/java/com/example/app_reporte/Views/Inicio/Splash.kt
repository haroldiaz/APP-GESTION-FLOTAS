package com.example.app_reporte.Views.Inicio

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.app_reporte.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController? = null) {


    LaunchedEffect(Unit) {
        delay(2000) // ⏰ Espera 2 segundos
        navController?.navigate("login") {
            popUpTo("Splash") { inclusive = true }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(60.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "Mi imagen",
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clip(RoundedCornerShape(16.dp))
        )

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSplash() {
    //SplashScreen()
}
