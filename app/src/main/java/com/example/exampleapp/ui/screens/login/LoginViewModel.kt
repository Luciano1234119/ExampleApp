package com.example.exampleapp.ui.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val errorMessage: String? = null,
    val isLoading: Boolean = false
) {
    fun isFormValid(): Boolean =
        email.isNotBlank() &&
                android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                password.isNotBlank()
}

class LoginViewModel : ViewModel() {

    var uiState by mutableStateOf(LoginUiState())
        private set

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun updateEmail(email: String) {
        uiState = uiState.copy(email = email, errorMessage = null)
    }

    fun updatePassword(password: String) {
        uiState = uiState.copy(password = password, errorMessage = null)
    }

    fun loginUser(onSuccess: () -> Unit) {
        if (!uiState.isFormValid()) {
            uiState = uiState.copy(errorMessage = "Correo o contraseña no válidos.")
            return
        }

        uiState = uiState.copy(isLoading = true, errorMessage = null)

        auth.signInWithEmailAndPassword(uiState.email, uiState.password)
            .addOnSuccessListener {
                uiState = uiState.copy(isLoading = false)
                onSuccess()
            }
            .addOnFailureListener { e ->
                uiState = uiState.copy(
                    isLoading = false,
                    errorMessage = "Error al iniciar sesión. Verifica tus credenciales."
                )
            }
    }
}