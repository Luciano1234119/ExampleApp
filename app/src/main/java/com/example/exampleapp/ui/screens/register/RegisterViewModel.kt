package com.example.exampleapp.ui.screens.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

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
                android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                password.length >= 6 &&
                password == confirmPassword
}


class RegisterViewModel : ViewModel() {

    var uiState by mutableStateOf(RegisterUiState())
        private set

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun updateName(name: String) {
        uiState = uiState.copy(name = name, errorMessage = null)
    }

    fun updateEmail(email: String) {
        uiState = uiState.copy(email = email, errorMessage = null)
    }

    fun updatePassword(password: String) {
        uiState = uiState.copy(password = password, errorMessage = null)
    }

    fun updateConfirmPassword(confirm: String) {
        uiState = uiState.copy(confirmPassword = confirm, errorMessage = null)
    }

    fun registerUser(onSuccess: () -> Unit) {
        if (!uiState.isFormValid()) {
            uiState = uiState.copy(errorMessage = "Por favor completa todos los campos correctamente.")
            return
        }

        uiState = uiState.copy(isLoading = true, errorMessage = null)

        auth.createUserWithEmailAndPassword(uiState.email, uiState.password)
            .addOnSuccessListener {
                uiState = uiState.copy(isLoading = false)
                onSuccess()
            }
            .addOnFailureListener { e ->
                uiState = uiState.copy(
                    isLoading = false,
                    errorMessage = mapFirebaseError(e.message)
                )
            }
    }

    private fun mapFirebaseError(errorCode: String?): String {
        return when (errorCode) {
            "ERROR_EMAIL_ALREADY_IN_USE" -> "El correo electrónico ya está en uso."
            "ERROR_INVALID_EMAIL" -> "El correo electrónico no es válido."
            "ERROR_WEAK_PASSWORD" -> "La contraseña es demasiado débil (mínimo 6 caracteres)."
            else -> "Error en el registro. Inténtalo de nuevo."
        }
    }
}