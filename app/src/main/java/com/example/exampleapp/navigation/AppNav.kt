package com.example.exampleapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.exampleapp.Screen // Importa la clase Screen
import com.example.exampleapp.ui.screens.alarm.AlarmScreen
import com.example.exampleapp.ui.screens.detail.DetailScreen
import com.example.exampleapp.ui.screens.graph.GraphScreen
import com.example.exampleapp.ui.screens.home.HomeScreen
import com.example.exampleapp.ui.screens.login.LoginScreen
import com.example.exampleapp.ui.screens.register.RegisterScreen

@Composable
fun AppNav(navController: NavHostController) {
    // Define el NavHost, comenzando en la pantalla de Login
    NavHost(navController = navController, startDestination = Screen.Login.route) {

        // --- Pantalla de Login (LA CORRECCIÓN ESTÁ AQUÍ) ---
        composable(Screen.Login.route) {
            LoginScreen(
                // Llama a LoginScreen SIN el parámetro 'onLoginClick'
                onLoginSuccess = {
                    // Si el login es exitoso, navega a Home y limpia la pila
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onRegisterClick = {
                    // Navega a la pantalla de registro
                    navController.navigate(Screen.Register.route)
                }
            )
        }

        // --- Pantalla de Registro ---
        composable(Screen.Register.route) {
            RegisterScreen(
                onRegisterSuccess = {
                    // Si el registro es exitoso, navega a Home
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Register.route) { inclusive = true }
                    }
                },
                onGoToLogin = {
                    // Regresa a la pantalla anterior (Login)
                    navController.popBackStack()
                }
            )
        }

        // --- Pantalla Home ---
        composable(Screen.Home.route) {
            HomeScreen(
                onGoToGraph = { navController.navigate(Screen.Graph.route) },
                onGoToAlarm = { navController.navigate(Screen.Alarm.route) }
            )
        }

        // --- Pantalla del Gráfico ---
        composable(Screen.Graph.route) {
            GraphScreen(onBack = { navController.popBackStack() })
        }

        // --- Pantalla de Alarma ---
        composable(Screen.Alarm.route) {
            AlarmScreen(onBack = { navController.popBackStack() })
        }

        // --- Pantalla de Detalle (con argumento) ---
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: "0"
            DetailScreen(id = id)
        }
    }
}