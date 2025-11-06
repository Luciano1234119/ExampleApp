package com.example.exampleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.Composable
import com.example.exampleapp.navigation.AppNav
import com.example.gasalert.ui.theme.GasAlertTheme


sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Welcome : Screen("welcome")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            AppNav(navController)
        }
    }
}

@Composable
fun LoginScreen() {
    Text("Bienvenido a GasAlert")
}
@Composable
fun LoginScreenPreview() {
    GasAlertTheme {
        LoginScreen()
    }
}