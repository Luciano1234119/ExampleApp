package com.example.exampleapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.exampleapp.ui.screens.login.LoginScreen
import com.example.exampleapp.ui.screens.register.RegisterScreen
import com.example.exampleapp.ui.screens.home.HomeScreen
import com.example.exampleapp.ui.screens.graph.GraphScreen
import com.example.exampleapp.ui.screens.alarm.AlarmScreen

@Composable
fun AppNav(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        // LOGIN
        composable("login") {
            LoginScreen(
                onLoginClick = {
                    navController.navigate("home")
                },
                onRegisterClick = {
                    navController.navigate("register")
                }
            )
        }

        // HOME
        composable("home") {
            HomeScreen(
                onGoToGraph = { navController.navigate("graph") },
                onGoToAlarm = { navController.navigate("alarm") }
            )
        }

        // REGISTER
        composable("register") {
            RegisterScreen(
                onRegisterSuccess = { navController.navigate("home") },
                onGoToLogin = { navController.navigate("login") }
            )
        }

        // GRAPH SCREEN
        composable("graph") {
            GraphScreen(
                onBack = { navController.popBackStack() }
            )
        }

        // ALARM SCREEN
        composable("alarm") {
            AlarmScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
