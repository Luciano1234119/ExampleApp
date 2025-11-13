package com.example.exampleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.exampleapp.navigation.AppNav
import com.example.exampleapp.ui.theme.GasAlertTheme // Corregido el import del Tema
import com.google.firebase.FirebaseApp // Import para inicializar Firebase

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home") // Cambiado 'Welcome' por 'Home' para consistencia
    object Graph : Screen("graph")
    object Alarm : Screen("alarm")
    object Detail : Screen("detail/{id}") { // Ruta para detalles con argumento
        fun createRoute(id: String) = "detail/$id"
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar Firebase
        FirebaseApp.initializeApp(this)

        setContent {
            // Usamos el tema personalizado de la app
            GasAlertTheme {
                val navController = rememberNavController()
                AppNav(navController)
            }
        }
    }
}