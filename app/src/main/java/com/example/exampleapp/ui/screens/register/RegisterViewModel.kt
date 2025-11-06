package com.example.exampleapp.ui.screens.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

data class RegisterUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val errorMessage: String? = null,
    val isLoading: Boolean = false
) {
    fun isFormValid(): Boolean =
        name.isNotBlank() &&
                email.isNotBlank() &&
                password.length >= 6 &&
                password == confirmPassword
}


class RegisterViewModel : ViewModel() {

    var uiState by androidx.compose.runtime.mutableStateOf(RegisterUiState())
        private set

    fun updateName(name: String) {
        uiState = uiState.copy(name = name)
    }

    fun updateEmail(email: String) {
        uiState = uiState.copy(email = email)
    }

    fun updatePassword(password: String) {
        uiState = uiState.copy(password = password)
    }

    fun updateConfirmPassword(confirm: String) {
        uiState = uiState.copy(confirmPassword = confirm)
    }

    /**
     * Simula un registro de usuario.
     * Si usas Firebase, reemplaza el bloque con la llamada a FirebaseAuth.
     */
    fun registerUser(onSuccess: () -> Unit) {
        if (!uiState.isFormValid()) {
            uiState = uiState.copy(errorMessage = "Por favor completa todos los campos correctamente")
            return
        }

        uiState = uiState.copy(isLoading = true, errorMessage = null)

        viewModelScope.launch {
            try {
                // 🔹 Ejemplo: Aquí puedes integrar Firebase
                // FirebaseAuth.getInstance().createUserWithEmailAndPassword(uiState.email, uiState.password)
                //     .addOnSuccessListener { onSuccess() }
                //     .addOnFailureListener { e -> uiState = uiState.copy(errorMessage = e.message) }

                // Simulación de éxito (puedes quitar esto al integrar Firebase)
                kotlinx.coroutines.delay(1500)
                uiState = uiState.copy(isLoading = false)
                onSuccess()

            } catch (e: Exception) {
                uiState = uiState.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Error desconocido"
                )
            }
        }
    }
}