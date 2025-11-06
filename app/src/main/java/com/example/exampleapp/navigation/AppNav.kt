package com.example.exampleapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.exampleapp.ui.screens.login.LoginScreen
import com.example.exampleapp.ui.screens.register.RegisterScreen
import com.example.exampleapp.ui.screens.home.HomeScreen

@Composable
fun AppNav(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
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

        composable("home") {
            HomeScreen()
        }

        composable("register") {
            RegisterScreen(
                onRegisterSuccess = { navController.navigate("home") },
                onGoToLogin = { navController.navigate("login") }
            )
        }
    }
}
