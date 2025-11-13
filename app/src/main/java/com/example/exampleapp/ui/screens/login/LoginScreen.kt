package com.example.exampleapp.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.exampleapp.R
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.exampleapp.ui.components.PasswordTextField // Importamos el PasswordTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit = {}, // Cambiado de onLoginClick a onLoginSuccess
    onRegisterClick: () -> Unit = {},
    onForgotPasswordClick: () -> Unit = {},
    loginViewModel: LoginViewModel = viewModel() // Inyectamos el ViewModel
    // Se eliminó el parámetro duplicado: onLoginClick: () -> Unit
) {
    val uiState = loginViewModel.uiState // Obtenemos el estado desde el ViewModel

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF8F8FB)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // 🖼️ Logo superior personalizado
            Image(
                painter = painterResource(id = R.drawable.gasalert_logo),
                contentDescription = "Logo GasAlert",
                modifier = Modifier
                    .size(150.dp)
                    .padding(bottom = 16.dp)
            )

            Text(
                text = "Bienvenido a GasAlert",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center
            )

            Text(
                text = "Detectamos el peligro antes de que tú lo respires.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                color = Color.Gray,
                modifier = Modifier.padding(top = 8.dp, bottom = 24.dp)
            )

            // ✉️ Campo de correo
            OutlinedTextField(
                value = uiState.email, // Conectado al ViewModel
                onValueChange = { loginViewModel.updateEmail(it) }, // Conectado al ViewModel
                label = { Text("Correo electrónico") },
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // 🔒 Campo de contraseña (Usando el Composable reutilizable)
            PasswordTextField(
                value = uiState.password, // Conectado al ViewModel
                onValueChange = { loginViewModel.updatePassword(it) }, // Conectado al ViewModel
                label = "Contraseña"
            )

            Spacer(modifier = Modifier.height(24.dp)) // Aumentado el espacio

            // 🔵 Botón de inicio de sesión
            Button(
                onClick = {
                    loginViewModel.loginUser(onLoginSuccess) // Llamada al ViewModel
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4A5CF5)
                ),
                enabled = !uiState.isLoading // Deshabilitado mientras carga
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(22.dp)
                    )
                } else {
                    Text("Iniciar sesión", color = Color.White, fontSize = 16.sp)
                }
            }

            // Mensaje de error
            if (uiState.errorMessage != null) {
                Text(
                    text = uiState.errorMessage!!,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 12.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 🔗 Texto de registro
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ClickableText(
                    text = AnnotatedString("¿No tienes cuenta? Regístrate"),
                    onClick = { onRegisterClick() },
                    style = LocalTextStyle.current.copy(
                        color = Color(0xFF4A5CF5),
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    MaterialTheme {
        LoginScreen()
    }
}