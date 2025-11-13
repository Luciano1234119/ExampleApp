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
    NavHost(navController = navController, startDestination = Screen.Login.route) {

        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onRegisterClick = {
                    navController.navigate(Screen.Register.route)
                }
            )
        }

        composable(Screen.Register.route) {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Register.route) { inclusive = true }
                    }
                },
                onGoToLogin = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.Home.route) {
            HomeScreen(
                onGoToGraph = { navController.navigate(Screen.Graph.route) },
                onGoToAlarm = { navController.navigate(Screen.Alarm.route) }
            )
        }

        composable(Screen.Graph.route) {
            GraphScreen(onBack = { navController.popBackStack() })
        }

        composable(Screen.Alarm.route) {
            AlarmScreen(onBack = { navController.popBackStack() })
        }

        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: "0"
            DetailScreen(id = id)
        }
    }
}